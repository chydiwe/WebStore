package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.ConfirmationToken;
import com.jackass.RestAPI.entity.User;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.mail.MailManager;
import com.jackass.RestAPI.repository.ConfirmationTokenRepository;
import com.jackass.RestAPI.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MailManager mailManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfirmationTokenRepository tokenRepository;

    @RequestMapping(method = RequestMethod.PUT)
    public void register(@RequestBody User user){
        User u = userRepository.getUserByEmail(user.getEmail());

        if(u != null){
            throw new AlreadyExistsException("User with such email already registered.");
        }

        int userId = userRepository.save(user).getId();

        ConfirmationToken token = new ConfirmationToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUserId(userId);

        tokenRepository.save(token);

        mailManager.sendToken(user.getEmail(), token.getToken());
    }

    @RequestMapping(method = RequestMethod.POST)
    public void confirm(@RequestParam String token){
        tokenRepository.deleteByToken(token);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<User> authenticate(@RequestParam String email, @RequestParam String password){
        User user = userRepository.getUserByEmailAndPassword(email, password);

        if(user == null){
            throw new NotFoundException("Wrong email or password.");
        }

        return ResponseEntity.ok().body(user);
    }

}
