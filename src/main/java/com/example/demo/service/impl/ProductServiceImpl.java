package com.example.demo.service.impl;

import com.example.demo.dto.product.ProductRequest;
import com.example.demo.dto.product.ProductResponse;
import com.example.demo.entites.Product;
import com.example.demo.enums.Type;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    @Override
    public ProductResponse getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty())
            throw new NotFoundException("product with id: "+id+" not found!", HttpStatus.BAD_GATEWAY);
        return productMapper.toDto(product.get());
    }

    @Override
    public void addProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrize(productRequest.getPrize());
        product.setCreated_date(LocalDateTime.now().toString());
        if (!containsType(productRequest.getType()))
            throw new BadRequestException("no type with name: "+productRequest.getType()+"!");
        product.setType(Type.valueOf(productRequest.getType()));
        productRepository.save(product);
    }

    @Override
    public List<ProductResponse> getAll() {
        return productMapper.toDtoS(productRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        if (productRepository.findById(id).isEmpty())
            throw new NotFoundException("the product with id: "+id+" is empty!", HttpStatus.BAD_REQUEST);
        productRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, ProductRequest productRequest) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty())
            throw new NotFoundException("the product with id: "+id+" is empty!", HttpStatus.BAD_REQUEST);
        product.get().setName(productRequest.getName());
        product.get().setDescription(productRequest.getDescription());
        product.get().setPrize(productRequest.getPrize());
        if (!containsType(productRequest.getType()))
            throw new BadRequestException("no type with name: "+productRequest.getType()+"!");
        product.get().setType(Type.valueOf(productRequest.getType()));
        productRepository.save(product.get());
    }

    private boolean containsType(String type) {
        for (Type type1:Type.values()){
            if (type1.name().equalsIgnoreCase(type))
                return true;
        }
        return false;
    }
}
