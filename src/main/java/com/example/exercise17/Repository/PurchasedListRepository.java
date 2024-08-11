package com.example.exercise17.Repository;

import com.example.exercise17.Model.PurchasedList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedListRepository extends JpaRepository<PurchasedList, Integer> {


}
