package com.example.FoodDeliveryApp.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartStatusResponse {

     String customerName;

     String address;

     String customerMobile;

     double cartTotal;

     List<FoodResponse> foodResponseList;

     String restaurantName;
}
