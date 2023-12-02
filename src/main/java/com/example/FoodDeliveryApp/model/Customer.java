package com.example.FoodDeliveryApp.model;

import com.example.FoodDeliveryApp.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Size(min = 5,message = "{name is too short}")
    @Size(max = 10,message = "{name is too long}")
    @Column(name = "name",nullable = false)
    String name;

    @Column(name = "mobile_no",nullable = false,unique = true)
    @Size(min=10,max=10)
    String mobileNo;

    @Email
    @Column(name = "email")
    String email;

    @Column(name = "address",nullable = false)
    String address;

    @Column(name = "gender",nullable = false)
    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<OrderEntity> orderEntityList = new ArrayList<>();

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    DebitCard debitCard;
}
