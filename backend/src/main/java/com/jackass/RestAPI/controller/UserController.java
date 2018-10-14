package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.User;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<User> authenticate(@RequestParam String email, @RequestParam String password){
        User user = userRepository.getUserByEmailAndPassword(email, password);
        if(user == null){
            throw new NotFoundException("Wrong email or password.");
        }
        return ResponseEntity.ok().body(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void register(User user){
//        User u = userRepository.getUserByEmail(user.getEmail());
//        if(u != null){
//            throw new BadRequestException("User with such email already registered.");
//        }
//        userRepository.addUser(user);
    }

}
