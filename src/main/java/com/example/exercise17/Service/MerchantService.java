package com.example.exercise17.Service;


import com.example.exercise17.Model.Merchant;
import com.example.exercise17.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class MerchantService {


    private final MerchantRepository merchantRepository;




    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    public void addMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    public boolean updateMerchant(int id , Merchant merchant) {
        Merchant oldMerchant = merchantRepository.getById(id);
        if (oldMerchant == null) {
            return false;
        }
        oldMerchant.setName(merchant.getName());
        merchantRepository.save(oldMerchant);
        return true;
    }

    public boolean deleteMerchant(int id) {
        Merchant merchant = merchantRepository.findById(id).get();
        if (merchant == null) {
            return false;
        }
        merchantRepository.delete(merchant);
        return true;
    }





}
