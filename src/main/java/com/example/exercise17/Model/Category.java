package com.example.exercise17.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer category_id ;


    @NotEmpty(message = "category name should be not empty")
    @Size(min = 3 , message = "category name length should be more than 3 characters")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String name;
}
