package com.example.exercise17.Controller;


import com.example.exercise17.Api.ApiResponse;
import com.example.exercise17.Model.User;
import com.example.exercise17.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/post")
    public ResponseEntity addUser(@Valid @RequestBody User user , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable int id, @Valid @RequestBody User user , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = userService.updateUser(id, user);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not found"));
    }

    @PutMapping("/buy/{uid}/{pid}/{mid}")
    public ResponseEntity buyUser(@PathVariable int uid, @PathVariable int pid, @PathVariable int mid) {
        String Buy = userService.buyProduct(uid, pid, mid);

        if (Buy.equalsIgnoreCase("Buy Success")){
            return ResponseEntity.status(200).body(new ApiResponse(Buy));
        }else{
            return ResponseEntity.status(400).body(new ApiResponse(Buy));
        }

    }


    @PutMapping("/restock/{uid}/{pid}/{mid}")
    public ResponseEntity returnProduct(@PathVariable int uid, @PathVariable int pid, @PathVariable int mid) {
        String ReStock = userService.returnProduct(uid, pid, mid);
        if (ReStock.equalsIgnoreCase("Return Success")) {
            return ResponseEntity.status(200).body(new ApiResponse(ReStock));
        }
        return ResponseEntity.status(400).body(new ApiResponse(ReStock));
    }


    @PutMapping("/addbalance/{uid}/{amount}")
    public ResponseEntity addBalance(@PathVariable int uid, @PathVariable int amount) {
        String add = userService.addBalance(uid, amount);
        if (add.equalsIgnoreCase("balance added")) {
            return ResponseEntity.status(200).body(new ApiResponse(add));
        }
        return ResponseEntity.status(400).body(new ApiResponse(add));
    }

    @PutMapping("/addtocart/{uid}/{pid}")
    public ResponseEntity addToCart(@PathVariable int uid, @PathVariable int pid) {
        String addToCart = userService.addToShoppingCart(uid, pid);
        if (addToCart.equalsIgnoreCase("Product Added to cart successfully")) {
            return ResponseEntity.status(200).body(new ApiResponse(addToCart));
        }
        return ResponseEntity.status(400).body(new ApiResponse(addToCart));
    }

    @PutMapping("/checkout/{uid}")
    public ResponseEntity checkOut(@PathVariable int uid) {
        String checkOut = userService.checkOut(uid);
        if (checkOut.equalsIgnoreCase("Buy Success")) {
            return ResponseEntity.status(200).body(new ApiResponse(checkOut));
        }
        return ResponseEntity.status(400).body(new ApiResponse(checkOut));
    }


}
