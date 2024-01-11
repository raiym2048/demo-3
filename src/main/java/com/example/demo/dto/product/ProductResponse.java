package com.example.demo.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private String type;
    private String description;
    private Integer prize;
    private String created_date;

}
