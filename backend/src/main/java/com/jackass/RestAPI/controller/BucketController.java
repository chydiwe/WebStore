package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.BucketItem;
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
    public ResponseEntity<?> getBucket(@RequestParam int userId) {
        User user = userRepository.getUserById(userId);

        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }

        return ResponseEntity.ok().body(user.getProducts());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteBucket(@RequestParam int userId) {
        User user = userRepository.getUserById(userId);

        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }

        Set<BucketItem> products = user.getProducts();
        if (products == null) {
            throw new NotFoundException("User does not have products in bucket.");
        }
        for (BucketItem b : products) {
            bucketRepository.delete(b);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, params = {"userId", "productId"})
    public void deleteFromBucket(@RequestParam int userId,
                                 @RequestParam int productId) {
        User user = userRepository.getUserById(userId);
        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }

        Set<BucketItem> products = user.getProducts();
        if (products == null) {
            throw new NotFoundException("User does not have products in bucket.");
        }

        Product product = productRepository.getProductById(productId);
        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }

        BucketItem elem = products.stream()
                .filter(bucket -> bucket.getProduct().getId() == productId)
                .findFirst()
                .orElse(null);
        if (elem == null) {
            throw new NotFoundException("Bucket does not have such product.");
        }
        bucketRepository.delete(elem);
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

        Set<BucketItem> products = user.getProducts();

        if (products != null) {
            BucketItem elem = products.stream()
                    .filter(bucket -> bucket.getProduct().getId() == productId)
                    .findFirst()
                    .orElse(null);
            if (elem == null) {
                elem = new BucketItem();
                elem.setUserId(user.getId());
                elem.setAmount(amount);
                elem.setProduct(product);
            } else {
                elem.setAmount(elem.getAmount() + amount);
            }
            bucketRepository.save(elem);
        } else {
            BucketItem bucketItem = new BucketItem();
            bucketItem.setUserId(userId);
            bucketItem.setProduct(product);
            bucketItem.setAmount(amount);
            bucketRepository.save(bucketItem);
        }
    }
}
