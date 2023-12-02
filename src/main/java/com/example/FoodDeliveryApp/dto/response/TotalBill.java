package com.example.FoodDeliveryApp.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalBill {

    int foodAmount;

    String couponAmount;

    String SGST;

    String CGST;

    int totalBillAmount;
}
