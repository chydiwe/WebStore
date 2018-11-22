package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.Category;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/products/categories")
//@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<Category>> getCategories() {
        Set<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addCategory(@RequestParam String name) {
        Set<Category> categories = categoryRepository.findAll();
        for (Category c : categories) {
            if (c.getName().equals(name)) {
                throw new AlreadyExistsException("Such category name already exists.");
            }
        }
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteCategory(@RequestParam int id) {
        Category category = categoryRepository.getCategoryById(id);
        if (category == null) {
            throw new NotFoundException("Wrong manufacturer ID.");
        }
        categoryRepository.delete(category);
    }

}
