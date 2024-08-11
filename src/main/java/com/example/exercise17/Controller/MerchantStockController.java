package com.example.exercise17.Controller;

import com.example.exercise17.Api.ApiResponse;
import com.example.exercise17.Model.MerchantStock;
import com.example.exercise17.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getMerchantStock() {
        return ResponseEntity.status(200).body(merchantStockService.getAllMerchantStocks());
    }

    @PostMapping("/post")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
            merchantStockService.addMerchantStock(merchantStock);
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Added"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable int id, @RequestBody MerchantStock merchantStock , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = merchantStockService.updateMerchantStock(id, merchantStock);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Updated"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Merchant Stock Not Found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable int id) {
        boolean isDeleted = merchantStockService.deleteMerchantStock(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Deleted Successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Merchant Stock Not Found"));
    }

    @PutMapping("/addstock/{pid}/{mid}/{amount}")
    public ResponseEntity addStock(@PathVariable int pid, @PathVariable int mid , @PathVariable int amount) {
        String Add = merchantStockService.addStocks(pid, mid, amount);
        if(Add.equalsIgnoreCase("Stock added successfully")) {
            return ResponseEntity.status(200).body(new ApiResponse(Add));
        }
        return ResponseEntity.status(400).body(new ApiResponse(Add));


    }

    @PutMapping("/discount/{pid}/{mid}/{discount}")
    public ResponseEntity discountProduct(@PathVariable int pid, @PathVariable int mid ,  @PathVariable double discount) {
        String discount1 = merchantStockService.discountProduct(pid, mid , discount);
        if (discount1.equalsIgnoreCase("price changed successfully")) {
            return ResponseEntity.status(200).body(new ApiResponse(discount1));
        }
        return ResponseEntity.status(400).body(new ApiResponse(discount1));
    }


}
