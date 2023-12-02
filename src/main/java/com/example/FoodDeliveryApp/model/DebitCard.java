package com.example.FoodDeliveryApp.model;

import com.example.FoodDeliveryApp.enums.CardStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "debit_card")
public class DebitCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "card_no",nullable = false,unique = true)
    @Size(min = 12,max = 12)
    String cardNo;

    @Column(name = "card_status",nullable = false)
    @Enumerated(EnumType.STRING)
    CardStatus cardStatus;

    @Column(name = "card_cvv",nullable = false)
    @Size(min = 3,max = 3,message = "CVV should be thee digits")
    String cardCvv;

    @Column(name = "balance",nullable = false)
    double balance;

}
