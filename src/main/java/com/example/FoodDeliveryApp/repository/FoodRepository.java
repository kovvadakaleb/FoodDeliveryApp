package com.example.FoodDeliveryApp.repository;

import com.example.FoodDeliveryApp.model.FoodItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<FoodItem,Integer> {


   @Modifying
   @Transactional
   @Query(value = "delete from food_item where id=:id",nativeQuery = true)
   void foodDelete(@Param("id") Integer id);
}
