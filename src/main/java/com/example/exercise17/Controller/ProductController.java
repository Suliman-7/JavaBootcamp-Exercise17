package com.example.exercise17.Controller;


import com.example.exercise17.Api.ApiResponse;
import com.example.exercise17.Model.Product;
import com.example.exercise17.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @PostMapping("/post")
    public ResponseEntity addProduct(@Valid @RequestBody Product product , Errors errors) {
        if (errors.hasErrors()) {
            String messaqe = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(messaqe);
        }
        productService.addProduct(product);

        return ResponseEntity.status(200).body(new ApiResponse("Product added successfully"));

    }

    @PutMapping("update/{id}")
    public ResponseEntity updateProduct(@PathVariable int id, @Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String messaqe = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(messaqe);
        }
        boolean isUpdated = productService.updateProduct(id, product);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Product updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Product deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
    }



    @GetMapping("/getbycategory/{cid}")
    public ResponseEntity getProductByCategory(@PathVariable int cid) {
        List<Product> products = productService.getProductsByCategory(cid);
        if(products.size()>0) {
            return ResponseEntity.status(200).body(products);
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
    }



}
