package com.example.demo.service;

import com.example.demo.dto.product.ProductRequest;
import com.example.demo.dto.product.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse getById(Long id);

    void addProduct(ProductRequest productRequest);

    List<ProductResponse> getAll();

    void deleteById(Long id);

    void updateById(Long id, ProductRequest productRequest);

    void addToBucker(Long productId, String token);
}
