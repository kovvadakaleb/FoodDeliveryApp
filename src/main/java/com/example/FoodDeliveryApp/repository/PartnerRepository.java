package com.example.FoodDeliveryApp.repository;

import com.example.FoodDeliveryApp.model.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<DeliveryPartner,Integer> {
    String boy = "select p from DeliveryPartner p order by rand() limit 1";
    @Query(value = boy)
    DeliveryPartner findRandomDeliveryBoy();

}
