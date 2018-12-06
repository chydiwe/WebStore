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

    //
    // GET
    //
    @RequestMapping(
            value = "?id={id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productRepository.getProductById(id);

        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }

        return ResponseEntity.ok().body(product);
    }

    @RequestMapping(
            value = "?name={name}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        Product product = productRepository.getProductByName(name);

        if (product == null) {
            throw new NotFoundException("Wrong product name.");
        }

        return ResponseEntity.ok().body(product);
    }

    @RequestMapping(
            value = "?categoryId={categoryId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Set<Product>> getProductByCategory(@PathVariable int categoryId) {
        Category categoryObj = categoryRepository.getCategoryById(categoryId);

        if (categoryObj == null) {
            throw new NotFoundException("Wrong category.");
        }

        Set<Product> products = productRepository
                .findAllByCategory(categoryObj/*, PageRequest.of(num-1, PAGE_SIZE)*/);

        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(
            value = "?manufacturerId={manufacturerId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Set<Product>> getProductByManufacturer(@PathVariable int manufacturerId) {
        Manufacturer manufacturerObj = manufacturerRepository.getManufacturerById(manufacturerId);

        if (manufacturerObj == null) {
            throw new NotFoundException("Wrong manufacturer.");
        }

        Set<Product> products = productRepository
                .findAllByManufacturer(manufacturerObj/*, PageRequest.of(num-1, PAGE_SIZE)*/);

        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<Product>> getPage() {
        Set<Product> products = productRepository.findAll(/*PageRequest.of(num-1, PAGE_SIZE)*/);
        return ResponseEntity.ok().body(products);
    }

    //
    //  POST
    //
    @RequestMapping(
            value = "?name={name}&categoryId={categoryId}&manufacturerId={manufacturerId}&cost={cost}&quantity={quantity}",
            method = RequestMethod.POST
    )
    public void addProduct (@PathVariable String name,
                            @PathVariable int categoryId,
                            @PathVariable int manufacturerId,
                            @PathVariable int cost,
                            @PathVariable int quantity) {

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

    @RequestMapping(
            value = "?id={id}&quantity={quantity}",
            method = RequestMethod.POST
    )
    public void changeQuantity (@PathVariable int id,
                                @PathVariable int quantity) {
        Product product = productRepository.getProductById(id);

        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }

        if (quantity <= 0) {
            throw new NotFoundException("Wrong quantity.");
        }
        product.setQuantity(quantity);
        productRepository.save(product);
    }

    @RequestMapping(
            value = "?id={id}&info={info}",
            method = RequestMethod.POST
    )
    public void changeInfo(@PathVariable int id,
                           @PathVariable String info) {
        Product product = productRepository.getProductById(id);

        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }

        product.setShortInfo(info);
        productRepository.save(product);
    }

    //
    //  DELETE
    //
    @RequestMapping(
            value = "?id={id}",
            method = RequestMethod.DELETE
    )
    public void deleteProduct(@PathVariable int id) {
        Product product = productRepository.getProductById(id);

        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }

        productRepository.delete(product);
    }
}