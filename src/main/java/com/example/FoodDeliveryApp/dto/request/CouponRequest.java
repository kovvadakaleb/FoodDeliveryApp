package com.example.FoodDeliveryApp.dto.request;

import com.example.FoodDeliveryApp.enums.CouponType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CouponRequest {

    String couponNo;

    int discount;

    CouponType couponType;
}
