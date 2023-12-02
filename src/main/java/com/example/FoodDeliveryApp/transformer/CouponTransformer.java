package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.request.CouponRequest;
import com.example.FoodDeliveryApp.model.Coupon;

public class CouponTransformer {

    public static Coupon CouponRequest_To_Coupon(CouponRequest couponRequest){
        return Coupon.builder()
                .couponNo(couponRequest.getCouponNo())
                .couponType(couponRequest.getCouponType())
                .discount(couponRequest.getDiscount())
                .build();
    }
}
