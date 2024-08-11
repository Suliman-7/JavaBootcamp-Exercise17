package com.example.exercise17.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class MerchantStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @NotNull(message = "product Id should be not empty")
    @Column(columnDefinition = "varchar(20) not null")
    private int productId;
    @NotNull(message = "Merchant Id should be not empty")
    @Column(columnDefinition = "int not null")
    private int merchantId;
    @NotNull(message = "stock should be not empty")
//    @Min(value = 11 , message = "stock should be initialize at least 10")
    @Column(columnDefinition = "int not null")
    private int stock;

}
