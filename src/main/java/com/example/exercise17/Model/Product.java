package com.example.exercise17.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @NotEmpty(message = "name should be not empty")
    @Size(min = 3 , message = "name length should be more than 3 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @NotNull(message = "price should be not empty")
    @Positive(message = "price must be positive number")
    @Column(columnDefinition = "double not null ")
    private double price;
    @NotNull(message = "category ID should be not empty")
    @Column(columnDefinition = "int not null")
    private int categoryID;


}
