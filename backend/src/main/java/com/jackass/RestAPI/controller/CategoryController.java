package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.Category;
import com.jackass.RestAPI.entity.SubCategory;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.CategoryRepository;
import com.jackass.RestAPI.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/products/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;

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
    public void deleteCategory(@RequestParam int categoryId) {
        Category category = categoryRepository.getCategoryById(categoryId);
        if (category == null) {
            throw new NotFoundException("Wrong category ID.");
        }
        Set<SubCategory> subCategories = subCategoryRepository.findAllByCategoryId(categoryId);
        for (SubCategory sc : subCategories) {
            subCategoryRepository.delete(sc);
        }
        categoryRepository.delete(category);
    }

}
