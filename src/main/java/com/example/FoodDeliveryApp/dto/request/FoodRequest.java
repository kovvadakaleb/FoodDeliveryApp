package com.example.FoodDeliveryApp.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodRequest {

    int requiredQuantity;

    String customerMobile;

    int menuId;
}
