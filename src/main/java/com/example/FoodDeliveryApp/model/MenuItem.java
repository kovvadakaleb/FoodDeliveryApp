package com.example.FoodDeliveryApp.model;

import com.example.FoodDeliveryApp.enums.FoodCategory;
import jakarta.persistence.*;
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
@Table(name = "menu")
public class MenuItem {

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
    Restaurant restaurant;

    @OneToMany(mappedBy = "menuItem",cascade = CascadeType.ALL)
    List<FoodItem> foodItemList = new ArrayList<>();
}
