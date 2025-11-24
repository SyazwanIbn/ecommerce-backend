package com.ecommerce.service;

import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    //create category
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    //get all category
    public List<Category> getAllCategories (){
        return categoryRepository.findAll();
    }

    //get category by id
    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }

    // delete category
    public void deleteCategoryById(Long id){
         categoryRepository.deleteById(id);
    }
}
