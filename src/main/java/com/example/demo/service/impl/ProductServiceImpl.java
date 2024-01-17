package com.example.demo.service.impl;

import com.example.demo.dto.product.ProductRequest;
import com.example.demo.dto.product.ProductResponse;
import com.example.demo.entites.Product;
import com.example.demo.entites.User;
import com.example.demo.enums.Role;
import com.example.demo.enums.Type;
import com.example.demo.exception.BadCredentialsException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final UserService userService;
    @Override
    public ProductResponse getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty())
            throw new NotFoundException("product with id: "+id+" not found!", HttpStatus.BAD_GATEWAY);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(product.get().getName());
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


        User user = new User();
        List<Product> products = new ArrayList<>();
        if (user.getUserProducts()!=null){
            products = user.getUserProducts();
        }
        products.add(product);
        user.setUserProducts(products);

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

    @Override
    public void addToBucker(Long productId, String token) {
        Optional<Product> product = productRepository.findById(productId);



        User user = userService.getUsernameFromToken(token);
        if (!user.getRole().equals(Role.STUDENT)){
            throw new BadCredentialsException();
        }
        List<Product> products = new ArrayList<>();
        if (!user.getUserProducts().isEmpty())
            products = user.getUserProducts();
        products.add(product.get());
        user.setUserProducts(products);

    }

    private boolean containsType(String type) {
        for (Type type1:Type.values()){
            if (type1.name().equalsIgnoreCase(type))
                return true;
        }
        return false;
    }
}
