package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.Bucket;
import com.jackass.RestAPI.entity.ConfirmationToken;
import com.jackass.RestAPI.entity.User;
import com.jackass.RestAPI.entity.Product;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.mail.MailManager;
import com.jackass.RestAPI.repository.BucketRepository;
import com.jackass.RestAPI.repository.ConfirmationTokenRepository;
import com.jackass.RestAPI.repository.ProductRepository;
import com.jackass.RestAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
    private BucketRepository bucketRepository;
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

    @RequestMapping(value = "/bucket", method = RequestMethod.GET, params = "id")
    public ResponseEntity<?> getBucket(@RequestParam int id) {
        User user = userRepository.getUserById(id);

        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }

        return ResponseEntity.ok().body(user.getProducts());
    }

    @RequestMapping(value = "/bucket", method = RequestMethod.POST)
    public void addToBucket(@RequestParam int userId,
                            @RequestParam String productName,
                            @RequestParam int amount) {
        Bucket bucket = bucketRepository.getBucketByUserId(userId);

        if (bucket == null) {
            bucket = new Bucket();

            Product product = productRepository.getProductByName(productName);

            if (product == null) {
                throw new NotFoundException("Wrong product name.");
            }

            bucket.setUserId(userId);
            bucket.setAmount(amount);

            List<Product> products = new ArrayList<>();
            products.add(product);
            bucket.setProducts(products);

            bucketRepository.save(bucket);
        } else {
            bucketRepository.delete(bucket);

            bucket.setAmount(bucket.getAmount() + amount);

            bucketRepository.save(bucket);
        }
    }

}
