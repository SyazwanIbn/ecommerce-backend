package com.ecommerce.service;

import com.ecommerce.dto.CategoryDTO;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import jakarta.annotation.Resource;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {


    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    //method: create product
    public ProductDTO createProduct(ProductDTO productDTO){
        //1. dapatkan id category yang dihantar
        Category category = categoryRepository.findById(productDTO.getCategory().getId())
                .orElseThrow(()->new RuntimeException(
                        "Category not found with this id: " +
                                productDTO.getCategory().getId()
                ));

        //2. Convert ProductDTO ke Product entity
        Product newProduct = convertToEntity(productDTO);
        newProduct.setCategory(category);

        //3. save product entity
        Product savedProduct = productRepository.save(newProduct);

        //4. convert Product Entity yang dah disave ke DTO
        return convertToDTO(savedProduct);

    }

    //method: get all product
    public List<ProductDTO> getAllProducts (){
        List<Product> products = productRepository.findAll();

        //mapping dari entity ke DTO
        return products.stream()
                .map(this::convertToDTO) // METHOD CONVERT KE DTO
                .collect(Collectors.toList());
    }

    //helper method untuk convert entity ke DTO
    private ProductDTO convertToDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setSku(product.getSku());
        productDTO.setPrice(product.getPrice());
        productDTO.setStockQuantity(product.getStockQuantity());
        productDTO.setActive(product.isActive());

        //mapping untuk category
        if(product.getCategory() != null){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(product.getCategory().getId());
            categoryDTO.setName(product.getCategory().getName());
            productDTO.setCategory(categoryDTO);
        }
        return productDTO;
    }

    //helper method untuk convert DTO ke entity
    private Product convertToEntity(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSku(productDTO.getSku());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setActive(productDTO.isActive());
        // category akan diset luar method ni
        return product;
    }

}

