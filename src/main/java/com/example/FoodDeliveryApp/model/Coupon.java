package com.example.FoodDeliveryApp.model;

import com.example.FoodDeliveryApp.enums.CouponType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "coupon_no",nullable = false,unique = true)
    String couponNo;

    @Column(name = "discount",nullable = false,unique = true)
    @Min(value = 5,message = "Discount must be atLeast 5%")
    @Max(value = 50,message = "Discount must be at most 50%")
    double discount;

    @Column(name = "coupon_type",nullable = false,unique = true)
    @Enumerated(EnumType.STRING)
    CouponType couponType;

    @OneToMany(mappedBy = "coupon",cascade = CascadeType.ALL)
    List<OrderEntity> orderEntities = new ArrayList<>();
}
