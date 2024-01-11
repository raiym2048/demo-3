package com.example.demo.mapper;

import com.example.demo.dto.product.ProductResponse;
import com.example.demo.entites.Product;

import java.util.List;

public interface ProductMapper {
    ProductResponse toDto(Product product);

    List<ProductResponse> toDtoS(List<Product> all);
}
