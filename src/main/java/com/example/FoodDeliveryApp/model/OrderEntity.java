package com.example.FoodDeliveryApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_entity")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "order_id")
    String orderId;

    @CreationTimestamp
    @Column(name = "date")
    Date date;

    @Column(name = "food_bill")
    double foodBill;

    @Column(name = "cgst")
    double CGST;

    @Column(name = "sgst")
    double SGST;

    @Column(name = "paid_amount")
    int paidAmount;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @ManyToOne
    @JoinColumn
    DeliveryPartner deliveryPartner;

    @OneToMany(mappedBy = "orderEntity",cascade = CascadeType.ALL)
    List<FoodItem> foodItemList = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Restaurant restaurant;

    @ManyToOne
    @JoinColumn
    Coupon coupon;
}
