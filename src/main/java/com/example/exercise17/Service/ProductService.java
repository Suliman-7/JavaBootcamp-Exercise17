package com.example.exercise17.Service;

import com.example.exercise17.Model.Product;
import com.example.exercise17.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public boolean updateProduct(int id , Product product) {
        Product product1 = productRepository.getById(id);
        if(product1 == null ){
            return false;
        }
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setCategoryID(product.getCategoryID());
        productRepository.save(product1);
        return true;
    }

    public boolean deleteProduct(int id) {
        Product product1 = productRepository.getById(id);
        if(product1 == null) {
            return false;
        }
        productRepository.delete(product1);
        return true;
    }



    public List<Product> getProductsByCategory(int cid) {
        List<Product> products = productRepository.findAll() ;
        List<Product> products1 = new ArrayList<>();
        for (Product product : products) {
            if(product.getCategoryID()==cid){
                products1.add(product);
            }
        }
        return products1;
    }

}
