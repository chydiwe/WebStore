package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.ConfirmationToken;
import com.jackass.RestAPI.entity.User;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.mail.MailManager;
import com.jackass.RestAPI.repository.ConfirmationTokenRepository;
import com.jackass.RestAPI.repository.ProductRepository;
import com.jackass.RestAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private MailManager mailManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfirmationTokenRepository tokenRepository;
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void register(@RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam String patronymic) {

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setPatronymic(patronymic);

        User u = userRepository.getUserByEmail(user.getEmail());

        if (u != null) {
            throw new AlreadyExistsException("User with such email already registered.");
        }

        int userId = userRepository.save(user).getId();

        ConfirmationToken token = new ConfirmationToken();
        token.setToken(UUID.randomUUID().toString());
        token.setId(userId);

        tokenRepository.save(token);

        mailManager.sendToken(user.getEmail(), token.getToken());
    }

    @RequestMapping(method = RequestMethod.POST, params = "token")
    public void confirm(@RequestParam String token) {
        tokenRepository.deleteByToken(token);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<User> authenticate(@RequestParam String email, @RequestParam String password) {
        User user = userRepository.getUserByEmailAndPassword(email, password);

        if (user == null) {
            throw new NotFoundException("Wrong email or password.");
        }

        return ResponseEntity.ok().body(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@RequestParam int id) {
        User user = userRepository.getUserById(id);

        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }

        return ResponseEntity.ok().body(user);
    }

}