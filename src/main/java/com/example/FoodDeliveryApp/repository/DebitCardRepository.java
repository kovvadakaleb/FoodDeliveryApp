package com.example.FoodDeliveryApp.repository;

import com.example.FoodDeliveryApp.model.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitCardRepository extends JpaRepository<DebitCard,Integer> {
}
