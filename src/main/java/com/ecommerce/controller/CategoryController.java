package com.ecommerce.controller;

import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //create category
    @PostMapping
    public ResponseEntity<Category> createCategory (@RequestBody Category category){
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    //getAllCategories
    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    //getCategoryById
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
        Optional<Category> category =  categoryService.getCategoryById(id);

        if(category.isPresent()){
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>("Category Not Found", HttpStatus.NOT_FOUND);
        }
    }

    //delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
