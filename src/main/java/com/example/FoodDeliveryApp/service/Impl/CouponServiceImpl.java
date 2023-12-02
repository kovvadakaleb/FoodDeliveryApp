package com.example.FoodDeliveryApp.service.Impl;

import com.example.FoodDeliveryApp.dto.request.CouponRequest;
import com.example.FoodDeliveryApp.model.Coupon;
import com.example.FoodDeliveryApp.repository.CouponRepository;
import com.example.FoodDeliveryApp.service.CouponService;
import com.example.FoodDeliveryApp.transformer.CouponTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponRepository couponRepository;
    @Override
    public String addCoupon(CouponRequest couponRequest) {
        Coupon coupon = CouponTransformer.CouponRequest_To_Coupon(couponRequest);
        couponRepository.save(coupon);
        return "Coupon Added Successfully";
    }
}
