package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.entity.SubCategory;
import com.jackass.RestAPI.exception.AlreadyExistsException;
import com.jackass.RestAPI.exception.NotFoundException;
import com.jackass.RestAPI.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/products/categories/subcategories")
@CrossOrigin(origins = "http://localhost:3000")
public class SubCategoryController {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<SubCategory>> getSubCategories() {
        Set<SubCategory> subCategories = subCategoryRepository.findAll();
        return ResponseEntity.ok().body(subCategories);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addSubCategory(@RequestParam int categoryId,
                               @RequestParam String name) {
        Set<SubCategory> subCategories = subCategoryRepository.findAll();
        for (SubCategory sc : subCategories) {
            if (sc.getName().equals(name)) {
                throw new AlreadyExistsException("Such subcategory name already exists.");
            }
        }
        subCategories = subCategoryRepository.findAllByCategoryId(categoryId);
        if (subCategories == null) {
            throw new NotFoundException("Wrong category ID.");
        }
        SubCategory subCategory = new SubCategory();
        subCategory.setName(name);
        subCategory.setCategoryId(categoryId);
        subCategoryRepository.save(subCategory);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteSubCategory(@RequestParam String name) {
        Set<SubCategory> subCategories = subCategoryRepository.findAll();
        SubCategory subCategory = null;
        for (SubCategory sc : subCategories) {
            if (sc.getName().equals(name)) {
                subCategory = sc;
                break;
            }
        }
        if (subCategory == null) {
            throw new NotFoundException("Wrong subcategory name.");
        }
        subCategoryRepository.delete(subCategory);
    }


}
