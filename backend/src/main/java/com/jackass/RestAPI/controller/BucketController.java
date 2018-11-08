package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.Bucket;
import com.jackass.RestAPI.entity.Product;
import com.jackass.RestAPI.entity.User;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.BucketRepository;
import com.jackass.RestAPI.repository.ProductRepository;
import com.jackass.RestAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/users/bucket")
@CrossOrigin(origins = "http://localhost:3000")
public class BucketController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BucketRepository bucketRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getBucket(@RequestParam int id) {
        User user = userRepository.getUserById(id);

        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }

        return ResponseEntity.ok().body(user.getProducts());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteBucket(@RequestParam int id) {
        User user = userRepository.getUserById(id);

        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }

        Set<Bucket> products = user.getProducts();
        for (Bucket b : products) {
            bucketRepository.delete(b);
        }
        user.setProducts(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addToBucket(@RequestParam int userId,
                            @RequestParam int productId,
                            @RequestParam int amount) {
        Product product = productRepository.getProductById(productId);
        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }

        User user = userRepository.getUserById(userId);
        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }

        Set<Bucket> products = user.getProducts();

        if (products != null) {
            Bucket elem = products.stream()
                    .filter(bucket -> bucket.getProduct().getId() == productId)
                    .findFirst()
                    .orElse(null);
            if (elem == null) {
                elem = new Bucket();
                elem.setAmount(amount);
                elem.setProduct(product);
            } else {
                elem.setAmount(elem.getAmount() + amount);
            }
            bucketRepository.save(elem);
        } else {
            Bucket bucket = new Bucket();
            bucket.setUserId(userId);
            bucket.setProduct(product);
            bucket.setAmount(amount);
            bucketRepository.save(bucket);
        }
    }
}
