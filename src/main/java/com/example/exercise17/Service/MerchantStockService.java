package com.example.exercise17.Service;

import com.example.exercise17.Model.Merchant;
import com.example.exercise17.Model.MerchantStock;
import com.example.exercise17.Model.Product;
import com.example.exercise17.Repository.MerchantRepository;
import com.example.exercise17.Repository.MerchantStockRepository;
import com.example.exercise17.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MerchantStockService {

//    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();
    private final MerchantStockRepository merchantStockRepository;
    private final ProductRepository productRepository;
    private final MerchantRepository merchantRepository;


    public List<MerchantStock> getAllMerchantStocks() {
        return merchantStockRepository.findAll();
    }

    public void addMerchantStock(MerchantStock merchantStock) {

        merchantStockRepository.save(merchantStock);

    }

    public boolean updateMerchantStock(int id , MerchantStock merchantStock) {
        MerchantStock merchantStock1 = merchantStockRepository.getById(id);
        if (merchantStock1 == null) {
            return false;
        }
        merchantStock1.setStock(merchantStock.getStock());
        merchantStock1.setMerchantId(merchantStock1.getMerchantId());
        merchantStock1.setProductId(merchantStock.getProductId());

        merchantStockRepository.save(merchantStock1);
        return true;
    }


    public boolean deleteMerchantStock(int id) {
        MerchantStock merchantStock = merchantStockRepository.getById(id);
        if (merchantStock == null) {
            return false;
        }
        merchantStockRepository.delete(merchantStock);
        return true;
    }


    public String discountProduct(int pid , int mid ,  double discount) {
        double percentage = discount / 100;

        Product p = productRepository.getById(pid);

        if (p == null) {
            return "invalid product id";
        }

        Merchant m = merchantRepository.getById(mid);

        if (m == null) {
            return "invalid merchant id";
        }

        p.setPrice(p.getPrice() - (percentage * p.getPrice()));
        productRepository.save(p);
        return "price changed successfully";
    }







    public String addStocks(int pid , int mid , int amount) {



        Product p = productRepository.getById(pid);

        if (p == null) {
            return "invalid product id";
        }

        MerchantStock ms = merchantStockRepository.getById(mid);

        if (ms == null) {
            return "invalid merchant id";
        }


        merchantStockRepository.getById(mid).setStock(merchantStockRepository.getById(mid).getStock() + amount);
        merchantStockRepository.save(ms);
        return "Stock added successfully";
    }



    }



