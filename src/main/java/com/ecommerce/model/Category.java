package com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "categories")
@ToString(exclude = "products")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    //relationship
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonIgnore // IGNORE field product waktu serialization ke JSON
    private Set<Product> products = new HashSet<>();

    //helper method
    public void addProduct(Product product){
        products.add(product);
        product.setCategory(this);
    }

    public void removeProduct(Product product){
        products.remove(product);
        product.setCategory(null);
    }
}
