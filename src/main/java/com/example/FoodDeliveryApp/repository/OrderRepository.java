package com.example.FoodDeliveryApp.repository;

import com.example.FoodDeliveryApp.model.DeliveryPartner;
import com.example.FoodDeliveryApp.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {

}
