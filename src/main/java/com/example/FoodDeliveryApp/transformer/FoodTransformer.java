package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.response.FoodResponse;
import com.example.FoodDeliveryApp.model.Customer;
import com.example.FoodDeliveryApp.model.FoodItem;

import java.util.List;
import java.util.stream.Collectors;

public class FoodTransformer {

    public static FoodResponse FoodItem_To_FoodResponse(FoodItem foodItem){
        return FoodResponse.builder()
                .dishName(foodItem.getMenuItem().getDishName())
                .price(foodItem.getMenuItem().getPrice())
                .foodCategory(foodItem.getMenuItem().getFoodCategory())
                .requiredQuantity(foodItem.getRequiredQuantity())
                .veg(foodItem.getMenuItem().isVeg())
                .build();
    }

    public static List<FoodResponse> PrepareListOfFoodResponse_From_FoodItem(Customer customer){
       return   customer.getCart().getFoodItemList()
                .stream()
                .map(foodItem -> FoodTransformer.FoodItem_To_FoodResponse(foodItem))
                .collect(Collectors.toList());
    }
}
