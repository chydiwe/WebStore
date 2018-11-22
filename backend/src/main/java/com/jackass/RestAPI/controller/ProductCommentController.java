package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.Product;
import com.jackass.RestAPI.entity.ProductComment;
import com.jackass.RestAPI.entity.User;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.ProductCommentRepository;
import com.jackass.RestAPI.repository.ProductRepository;
import com.jackass.RestAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("api/products/comments")
@CrossOrigin(origins = "http://localhost")
public class ProductCommentController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCommentRepository productCommentRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void addComment(@RequestParam int productId,
                           @RequestParam int userId,
                           @RequestParam String comment,
                           @RequestParam LocalDate date) {
        Product product = productRepository.getProductById(productId);
        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }

        User user = userRepository.getUserById(userId);
        if (user == null) {
            throw new NotFoundException("Wrong user ID.");
        }

        ProductComment productComment = new ProductComment();
        productComment.setProductId(productId);
        productComment.setUserId(userId);
        productComment.setComment(comment);
        productComment.setDate(date);
        productCommentRepository.save(productComment);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getComments(@RequestParam int productId) {
        Product product = productRepository.getProductById(productId);
        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }

        return ResponseEntity.ok().body(product.getComments());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteComments(@RequestParam int productId,
                               @RequestParam String comment) {
        Product product = productRepository.getProductById(productId);
        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }

        Set<ProductComment> productComments = product.getComments();
        for (ProductComment pc : productComments) {
            if (pc.getComment().equals(comment)) {
                productCommentRepository.delete(pc);
                return;
            }
        }
        throw new NotFoundException("No such comment.");
    }
}
