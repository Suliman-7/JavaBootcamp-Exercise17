package com.example.exercise17.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @NotEmpty(message = "username should be not empty")
    @Size(min = 6 , message = "username length should be more than 5 characters")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;
    @NotEmpty(message = "password should be not empty")
    @Size(min = 7 , message = "password length should be more than 6 characters")
    @Column(columnDefinition = "varchar(8) not null")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$", message = "password should have letters and digits")
    private String password;
    @Email
    @NotEmpty(message = "email should be not empty")
    @Column(columnDefinition = "varchar(8) not null unique")
    private String email;
    @NotEmpty(message = "role should be not empty")
    @Pattern(regexp = "^(Admin|Customer)" , message = "role should be either admin or customer")
    @Column(columnDefinition = "varchar(8) not null")
    private String role;
    @NotNull(message = "balance should be not empty")
    @Positive(message = "balance should be positive")
    @Column(columnDefinition = "double not null")
    private double balance;



}
