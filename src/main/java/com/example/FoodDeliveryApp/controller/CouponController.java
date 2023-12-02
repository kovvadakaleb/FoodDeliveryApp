package com.example.FoodDeliveryApp.controller;

import com.example.FoodDeliveryApp.dto.request.CouponRequest;
import com.example.FoodDeliveryApp.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;
    @PostMapping("/add")
    public ResponseEntity addCoupon(@RequestBody  CouponRequest couponRequest){
        String response = couponService.addCoupon(couponRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
