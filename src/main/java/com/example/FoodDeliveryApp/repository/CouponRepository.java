package com.example.FoodDeliveryApp.repository;

import com.example.FoodDeliveryApp.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Integer> {

    @Query(value = "select c from Coupon c order by RAND() limit 1")
    Coupon findrandomCoupon();
}
