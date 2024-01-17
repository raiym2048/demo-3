package com.example.demo.controller;

import com.example.demo.dto.product.ProductRequest;
import com.example.demo.dto.product.ProductResponse;
import com.example.demo.entites.User;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product") // its for all endpoints in the class: localhost:8080/product/...
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    @GetMapping("/{id}")
    public ProductResponse productResponse(@PathVariable Long id){
        return productService.getById(id);
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody ProductRequest productRequest){
        productService.addProduct(productRequest);
    }

    @GetMapping("/getAll")
    public List<ProductResponse> productResponses(){
        return productService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void updateById(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        productService.updateById(id, productRequest);
    }

    @GetMapping("/{userId}")
    public List<ProductResponse> userProducts(@PathVariable Long userId){

        Optional<User> user = userRepository.findById(userId);

        return productMapper.toDtoS(user.get().getUserProducts());
    }
    @PostMapping("/addToBucket/{productId}")
    public void aVoid(@PathVariable Long productId, @RequestHeader("Authorization") String token){
        productService.addToBucker(productId,token );
    }

}
