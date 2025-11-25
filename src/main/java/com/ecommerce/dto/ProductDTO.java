package com.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonPropertyOrder({ // untuk sort JSON
        "id",
        "name",
        "description",
        "sku",
        "price",
        "stockQuantity",
        "active",
        "category"
})
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private String sku;
    private BigDecimal price;
    private Integer stockQuantity;
    private boolean active;
    private CategoryDTO category;


}
