package com.example.FoodDeliveryApp.model;

import com.example.FoodDeliveryApp.enums.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "food_item")
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "dish_name",nullable = false)
    String dishName;

    @Column(name = "price",nullable = false)
    double price;

    @Column(name = "veg",nullable = false)
    boolean veg;

    @Column(name = "available",nullable = false)
    boolean available;

    @Column(name = "food_category")
    @Enumerated(EnumType.STRING)
    FoodCategory foodCategory;

    @ManyToOne
    @JoinColumn
    Cart cart;

    @ManyToOne
    @JoinColumn
    OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn
    Restaurant restaurant;
}
