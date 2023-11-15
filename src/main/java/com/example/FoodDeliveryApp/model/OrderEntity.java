package com.example.FoodDeliveryApp.model;

import jakarta.persistence.*;
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

    @Column(name = "price")
    double price;

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
}
