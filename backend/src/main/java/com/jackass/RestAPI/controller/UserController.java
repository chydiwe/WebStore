package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.ConfirmationToken;
import com.jackass.RestAPI.entity.Group;
import com.jackass.RestAPI.entity.User;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.mail.MailManager;
import com.jackass.RestAPI.repository.ConfirmationTokenRepository;
import com.jackass.RestAPI.repository.GroupRepository;
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
    @Autowired
    private GroupRepository groupRepository;

    //
    //  GET
    //
    @RequestMapping(
            value = "?id={id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user = userRepository.getUserById(id);

        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }

        return ResponseEntity.ok().body(user);
    }

    @RequestMapping(
            value = "?email={email}&password={password}",
            method = RequestMethod.GET
    )
    public ResponseEntity<User> authenticate(@PathVariable String email,
                                             @PathVariable String password) {
        User user = userRepository.getUserByEmailAndPassword(email, password);

        if (user == null) {
            throw new NotFoundException("Wrong email or password.");
        }

        return ResponseEntity.ok().body(user);
    }

    //
    //  POST
    //
    @RequestMapping(
            value = "?email={email}&password={password}&name={name}&surname={surname}&patronymic={patronymic}",
            method = RequestMethod.POST
    )
    public void register(@PathVariable String email,
                         @PathVariable String password,
                         @PathVariable String name,
                         @PathVariable String surname,
                         @PathVariable String patronymic) {

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

    @RequestMapping(
            value = "?token={token}",
            method = RequestMethod.POST
    )
    public void confirm(@PathVariable String token) {
        ConfirmationToken tokenObj = tokenRepository.getConfirmationTokenByToken(token);
        tokenRepository.delete(tokenObj);
    }

    @RequestMapping(
            value = "?id={id}&patronymic={patronymic}",
            method = RequestMethod.POST
    )
    public void changePatr(@PathVariable int id,
                           @PathVariable String patronymic) {
        User user = userRepository.getUserById(id);

        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }

        user.setPatronymic(patronymic);
        userRepository.save(user);
    }

    @RequestMapping(
            value = "?id={id}&group={group}",
            method = RequestMethod.POST)
    public void changeGroup(@PathVariable int id,
                            @PathVariable int group) {
        User user = userRepository.getUserById(id);

        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }

        Group groupObj = groupRepository.getGroupById(group);

        if (groupObj == null) {
            throw new NotFoundException("Wrong group ID.");
        }

        user.setGroup(groupObj);
        userRepository.save(user);
    }

    @RequestMapping(
            value = "?id={id}&phone={phone}",
            method = RequestMethod.POST
    )
    public void changePhone(@PathVariable int id,
                            @PathVariable String phone) {
        User user = userRepository.getUserById(id);

        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }

        user.setPhoneNumber(phone);
        userRepository.save(user);
    }

    //
    //  DELETE
    //
    @RequestMapping(
            value = "?deletePatronymic={id}",
            method = RequestMethod.DELETE
    )
    public void deletePatr(@PathVariable int id) {
        User user = userRepository.getUserById(id);

        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }

        user.setPatronymic(null);
        userRepository.save(user);
    }

    @RequestMapping(
            value = "?deletePhone={id}",
            method = RequestMethod.DELETE
    )
    public void deletePhone(@PathVariable int id) {
        User user = userRepository.getUserById(id);

        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }

        user.setPhoneNumber(null);
        userRepository.save(user);
    }

    @RequestMapping(
            value = "?id={id}",
            method = RequestMethod.DELETE
    )
    public void deleteUser(@PathVariable int id) {
        User user = userRepository.getUserById(id);

        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }
        userRepository.delete(user);
    }
}