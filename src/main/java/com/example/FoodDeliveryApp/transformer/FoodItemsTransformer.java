package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.request.FoodItemRequest;
import com.example.FoodDeliveryApp.dto.response.FoodItemResponse;
import com.example.FoodDeliveryApp.model.Customer;
import com.example.FoodDeliveryApp.model.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class FoodItemsTransformer {

    public static FoodItemResponse FoodItem_To_FoodItemResponse(FoodItem foodItem){
        return FoodItemResponse.builder()
                .foodCategory(foodItem.getFoodCategory())
                .veg(foodItem.isVeg())
                .price(foodItem.getPrice())
                .dishName(foodItem.getDishName())
                .build();
    }

   public static FoodItem FoodItemRequest_To_FoodItem(FoodItemRequest foodItemRequest){
        return FoodItem.builder()
                .foodCategory(foodItemRequest.getFoodCategory())
                .price(foodItemRequest.getPrice())
                .veg(foodItemRequest.isVeg())
                .available(true)
                .dishName(foodItemRequest.getDishName())
                .build();
   }
}
