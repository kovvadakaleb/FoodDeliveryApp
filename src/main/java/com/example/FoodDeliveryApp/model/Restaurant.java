package com.example.FoodDeliveryApp.model;

import com.example.FoodDeliveryApp.enums.RestaurantCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Size(min = 3,message = "{name is too short}")
    @Size(max = 10,message = "{name is too long}")
    @Column(name = "name",nullable = false,unique = true)
    String name;

    @Column(name = "restaurant_category")
    @Enumerated(EnumType.STRING)
    RestaurantCategory restaurantCategory;

    @Column(name = "location",nullable = false)
    String location;

    @Column(name = "contact_no",nullable = false,unique = true)
    @Size(min=10,max=10)
    String contactNo;

    @Column(name = "opened",nullable = false)
    boolean opened;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    List<OrderEntity> orderEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    List<FoodItem> foodItemList = new ArrayList<>();
}
