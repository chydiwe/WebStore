package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.Category;
import com.jackass.RestAPI.entity.Manufacturer;
import com.jackass.RestAPI.entity.Product;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private ProductCommentRepository productCommentRepository;

    private static final int PAGE_SIZE = 20;

    @RequestMapping(method = RequestMethod.POST)
    public void addProduct (@RequestParam String name,
                            @RequestParam int categoryId,
                            @RequestParam int manufacturerId,
                            @RequestParam int cost,
                            @RequestParam int quantity) {

        Product product = productRepository.getProductByName(name);

        if (product != null ) {
            throw new AlreadyExistsException("Product with such name already exists.");
        }

        Category categoryObj = categoryRepository.getCategoryById(categoryId);

        if (categoryObj == null) {
            throw new NotFoundException("Category with such id does not exists.");
        }

        Manufacturer manufacturerObj = manufacturerRepository.getManufacturerById(manufacturerId);

        if (manufacturerObj == null) {
            throw new NotFoundException("Manufacturer with such id does not exists.");
        }

        product = new Product();

        product.setName(name);
        product.setCategory(categoryObj);
        product.setManufacturer(manufacturerObj);
        product.setCost(cost);
        product.setQuantity(quantity);

        productRepository.save(product);
    }

    @RequestMapping(method = RequestMethod.POST, params = "quantity")
    public void changeQuantity (@RequestParam int id,
                                @RequestParam int quantity) {
        Product product = productRepository.getProductById(id);

        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }

        if (quantity <= 0) {
            throw new NotFoundException("Wrong quantity.");
        }
        product.setQuantity(quantity);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Product> getProductById(@RequestParam int id) {
        Product product = productRepository.getProductById(id);

        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }

        return ResponseEntity.ok().body(product);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteProduct(@RequestParam int id) {
        Product product = productRepository.getProductById(id);

        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }

        productRepository.delete(product);
    }

    @RequestMapping(method = RequestMethod.GET, params = "name")
    public ResponseEntity<Product> getProductByName(@RequestParam String name) {
        Product product = productRepository.getProductByName(name);

        if (product == null) {
            throw new NotFoundException("Wrong product name.");
        }

        return ResponseEntity.ok().body(product);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, params = "category")
    public ResponseEntity<Set<Product>> getProductByCategory(@RequestParam int categoryId/*,
                                                             @RequestParam int num*/) {
        Category categoryObj = categoryRepository.getCategoryById(categoryId);

        if (categoryObj == null) {
            throw new NotFoundException("Wrong category.");
        }

        Set<Product> products = productRepository
                .findAllByCategory(categoryObj/*, PageRequest.of(num-1, PAGE_SIZE)*/);

        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, params = "manufacturer")
    public ResponseEntity<Set<Product>> getProductByManufacturer(@RequestParam int manufacturerId/*,
                                                                 @RequestParam int num*/) {
        Manufacturer manufacturerObj = manufacturerRepository.getManufacturerById(manufacturerId);

        if (manufacturerObj == null) {
            throw new NotFoundException("Wrong manufacturer.");
        }

        Set<Product> products = productRepository
                .findAllByManufacturer(manufacturerObj/*, PageRequest.of(num-1, PAGE_SIZE)*/);

        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Set<Product>> getPage(/*@RequestParam int num*/) {
        Set<Product> products = productRepository.findAll(/*PageRequest.of(num-1, PAGE_SIZE)*/);
        return ResponseEntity.ok().body(products);
    }

}