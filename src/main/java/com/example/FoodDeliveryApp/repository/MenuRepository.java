package com.example.FoodDeliveryApp.repository;

import com.example.FoodDeliveryApp.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem,Integer> {

}
