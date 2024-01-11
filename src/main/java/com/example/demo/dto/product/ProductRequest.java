package com.example.demo.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private String type;
    private String description;
    private Integer prize;

}
