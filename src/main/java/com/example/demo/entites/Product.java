package com.example.demo.entites;


import com.example.demo.enums.Type;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String created_date;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Integer prize;
    private String description;

    @ManyToOne()
    User owner;
}
