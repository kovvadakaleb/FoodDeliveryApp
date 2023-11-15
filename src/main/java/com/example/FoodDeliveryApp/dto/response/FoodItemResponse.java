package com.example.FoodDeliveryApp.dto.response;

import com.example.FoodDeliveryApp.enums.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodItemResponse {
    String dishName;

    double price;

    boolean veg;

    FoodCategory foodCategory;
}
