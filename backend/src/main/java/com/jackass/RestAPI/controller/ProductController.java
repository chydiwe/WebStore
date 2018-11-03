package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.Category;
import com.jackass.RestAPI.entity.Manufacturer;
import com.jackass.RestAPI.entity.Product;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.CategoryRepository;
import com.jackass.RestAPI.repository.ManufacturerRepository;
import com.jackass.RestAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void addProduct (@RequestParam String name,
                            @RequestParam String category,
                            @RequestParam String manufacturer,
                            @RequestParam int cost,
                            @RequestParam int quantity) {

        Product product = productRepository.getProductByName(name);

        if (product != null ) {
            throw new AlreadyExistsException("Product with such name already exists.");
        }

        Category categoryObj = categoryRepository.getCategoryByName(category);

        if (categoryObj == null) {
            throw new NotFoundException("Category with such id does not exists.");
        }

        Manufacturer manufacturerObj = manufacturerRepository.getManufacturerByName(manufacturer);

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

    @RequestMapping(method = RequestMethod.GET, params = "id")
    public ResponseEntity<Product> getProductById(@RequestParam int id) {
        Product product = productRepository.getProductById(id);

        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }

        return ResponseEntity.ok().body(product);
    }

    @RequestMapping(method = RequestMethod.GET, params = "name")
    public ResponseEntity<Product> getProductByName(@RequestParam String name) {
        Product product = productRepository.getProductByName(name);

        if (product == null) {
            throw new NotFoundException("Wrong product name.");
        }

        return ResponseEntity.ok().body(product);
    }

    @RequestMapping(method = RequestMethod.GET, params = "category")
    public ResponseEntity<List<Product>> getProductByCategory(@RequestParam String category) {
        Category categoryObj = categoryRepository.getCategoryByName(category);

        if (categoryObj == null) {
            throw new NotFoundException("Wrong category name.");
        }

        List<Product> products = productRepository.findAllByCategory(categoryObj);

        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(method = RequestMethod.GET, params = "manufacturer")
    public ResponseEntity<List<Product>> getProductByManufacturer(@RequestParam String manufacturer) {
        Manufacturer manufacturerObj = manufacturerRepository.getManufacturerByName(manufacturer);

        if (manufacturerObj == null) {
            throw new NotFoundException("Wrong manufacturer name.");
        }

        List<Product> products = productRepository.findAllByManufacturer(manufacturerObj);

        return ResponseEntity.ok().body(products);
    }

}