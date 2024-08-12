package com.example.exercise17.Service;


import com.example.exercise17.Model.*;
import com.example.exercise17.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ProductRepository productRepository;
    private final MerchantStockRepository merchantStockRepository;
    private final PurchasedListRepository purchasedListRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public boolean updateUser(int id, User user) {
        User oldUser = userRepository.getById(id);
        if (oldUser == null) {
            return false;
        }
        oldUser.setBalance(user.getBalance());
        oldUser.setUsername(user.getUsername());
        oldUser.setRole(user.getRole());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        userRepository.save(oldUser);
        return true;
    }

    public boolean deleteUser(int id) {
        User user = userRepository.getById(id);
        if (user == null) {
            return false;
        }
        userRepository.delete(user);
        return true;
    }


    public String buyProduct(int uid, int pid, int mid) {

        User user = userRepository.getById(uid);

        if (user == null) {
            return "invalid user id";
        }


        Product p = productRepository.getById(pid);

        if (p == null) {
            return "invalid product id";
        }

        MerchantStock ms = merchantStockRepository.getById(mid);


        if (ms == null) {
            return "invalid merchant id";
        }

        if(userRepository.getById(uid).getRole().equalsIgnoreCase("Admin")){
            return "You can't buy because you are Admin";
        }



        if (userRepository.getById(uid).getBalance() < productRepository.getById(pid).getPrice()) {
            return "insufficient Balance";
        } else {

            if (merchantStockRepository.getById(mid).getStock() <= 0) {
                return "No Stock Available";
            } else {

            merchantStockRepository.getById(mid).setStock(merchantStockRepository.getById(mid).getStock() - 1);
            userRepository.getById(uid).setBalance(userRepository.getById(uid).getBalance() - productRepository.getById(pid).getPrice());
            PurchasedList purchasedList = new PurchasedList();
            Product p1 = productRepository.getById(pid);
            purchasedList.setId(p1.getId());
            purchasedList.setName(p1.getName());
            purchasedList.setPrice(p1.getPrice());
            purchasedList.setCategoryID(p1.getCategoryID());
            purchasedListRepository.save(purchasedList);
            userRepository.save(user);
            merchantStockRepository.save(ms);
            


                return "Buy Success";
                }
            }


    }




    public String returnProduct(int uid, int pid, int mid) {

        User user = userRepository.getById(uid);

        if (user == null) {
            return "invalid user id";
        }


        Product p = productRepository.getById(pid);

        if (p == null) {
            return "invalid product id";
        }

        MerchantStock ms = merchantStockRepository.getById(mid);


        if (ms == null) {
            return "invalid merchant id";
        }

        if (userRepository.getById(uid).getRole().equalsIgnoreCase("Admin")) {
            return "You can't return because you are Admin";
        }


            if (purchasedListRepository.getById(pid) != null) {
                merchantStockRepository.getById(mid).setStock(merchantStockRepository.getById(mid).getStock() + 1);
                userRepository.getById(uid).setBalance(userRepository.getById(uid).getBalance() + productRepository.getById(pid).getPrice());
                purchasedListRepository.delete(purchasedListRepository.getById(pid));
                merchantStockRepository.save(ms);
                userRepository.save(user);


                return "Return Success";
            }

        return "sorry you didn't by the product" ;

    }







    public String addBalance(int uid , int amount) {
        User user = userRepository.getById(uid);
        if (user == null) {
            return "invalid user id";
        }
        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);
        return "balance added";
    }








    public String addToShoppingCart(Integer uid, Integer pid ) {

        User user = userRepository.getById(uid);

        if (user == null) {
            return "invalid user id";
        }


        Product p = productRepository.getById(pid);

        if (p == null) {
            return "invalid product id";
        }


        if(userRepository.getById(uid).getRole().equalsIgnoreCase("Admin")){
            return "You can't add to cart because you are Admin";
        }

        ShoppingCart shoppingCart = new ShoppingCart();
        Product p1 = productRepository.getById(pid);
        shoppingCart.setId(p1.getId());
        shoppingCart.setName(p1.getName());
        shoppingCart.setPrice(p1.getPrice());
        shoppingCart.setCategoryID(p1.getCategoryID());
        shoppingCartRepository.save(shoppingCart);
        userRepository.save(user);

        return "Product Added to cart successfully";
    }







    public String checkOut(int uid) {


        // check the user id

        User user = userRepository.getById(uid);

        if (user == null) {
            return "invalid user id";
        }

        // To ensure the role is customer

        if(userRepository.getReferenceById(uid).getRole().equalsIgnoreCase("Admin")){
            return "You can't buy because you are Admin";
        }

        // check the shopping cart

        if(shoppingCartRepository.findAll().size() == 0){
            return "Shopping cart is Empty";
        }

        // check the balance

        int sum = 0 ;
        for(ShoppingCart sh : shoppingCartRepository.findAll()){
            sum += sh.getPrice();
            if(sum>userRepository.getById(uid).getBalance()){
                return "Insufficient Balance";
            }
        }


        for(ShoppingCart sh : shoppingCartRepository.findAll()) {
            userRepository.getById(uid).setBalance(userRepository.getById(uid).getBalance() - sh.getPrice());
            merchantStockRepository.getById(sh.getId()).setStock(merchantStockRepository.getById(sh.getId()).getStock() - 1);


            PurchasedList purchasedList = new PurchasedList();
            purchasedList.setId(sh.getId());
            purchasedList.setName(sh.getName());
            purchasedList.setPrice(sh.getPrice());
            purchasedList.setCategoryID(sh.getCategoryID());
            purchasedListRepository.save(purchasedList);
            userRepository.save(user);
            merchantStockRepository.save(merchantStockRepository.getById(sh.getId()));


        }

        for (PurchasedList pl : purchasedListRepository.findAll()) {
            shoppingCartRepository.delete(shoppingCartRepository.getById(pl.getId()));
        }



        return "Buy Success";

    }




}
