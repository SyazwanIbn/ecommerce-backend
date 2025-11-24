package com.ecommerce.repository;

import com.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //spring boot akan implement
    //save
    //findById
    //finaAll
    //deleteById


    //Optional, custom method untuk cari produk by name
    List<Product> findByNameContainingIgnoringCase(String name);

}

