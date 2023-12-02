package com.example.FoodDeliveryApp.dto.request;

import com.example.FoodDeliveryApp.enums.CardStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebitCardRequest {

    String cardNo;

    CardStatus cardStatus;

    String cardCvv;

    double balance;
}
