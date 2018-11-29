package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.Product;
import com.jackass.RestAPI.entity.ProductImage;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.ProductImageRepository;
import com.jackass.RestAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/products/images")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductImageController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;

    //
    //  GET
    //
    @RequestMapping(
            value = "id={id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getImages(@PathVariable int id) {
        Product product = productRepository.getProductById(id);
        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }
        return ResponseEntity.ok().body(product.getImages());
    }

    //
    //  POST
    //
    @RequestMapping(
            value = "id={id}&URI={URI}",
            method = RequestMethod.POST
    )
    public void addImage(@PathVariable int id,
                         @PathVariable String URI) {
        Product product = productRepository.getProductById(id);
        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }

        ProductImage productImage = new ProductImage();
        productImage.setProductId(id);
        productImage.setImage(URI);
        productImageRepository.save(productImage);
    }

    //
    //  DELETE
    //
    @RequestMapping(
            value = "id={id}&URI={URI}",
            method = RequestMethod.DELETE
    )
    public void deleteImage(@PathVariable int id,
                            @PathVariable String URI) {
        Product product = productRepository.getProductById(id);
        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }

        Set<ProductImage> productImages = product.getImages();

        for (ProductImage pi : productImages) {
            if (pi.getImage().equals(URI)){
                productImageRepository.delete(pi);
                return;
            }
        }
        throw new NotFoundException("Wrong image URI.");
    }

    @RequestMapping(
            value = "id={id}",
            method = RequestMethod.DELETE
    )
    public void deleteAllImages(@PathVariable int id) {
        Product product = productRepository.getProductById(id);
        if (product == null) {
            throw new NotFoundException("Wrong product ID.");
        }

        Set<ProductImage> productImages = product.getImages();
        for (ProductImage pi : productImages) {
            productImageRepository.delete(pi);
        }
    }

}
