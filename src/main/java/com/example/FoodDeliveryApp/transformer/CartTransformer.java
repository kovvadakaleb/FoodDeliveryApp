package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.response.CartResponse;
import com.example.FoodDeliveryApp.dto.response.FoodItemResponse;
import com.example.FoodDeliveryApp.model.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CartTransformer {

    public static CartResponse Cart_To_CartResponse(Customer customer){

        List<FoodItemResponse> foodItemResponseList = customer.getCart().getFoodItemList()
                .stream()
                .map(foodItem -> FoodItemsTransformer.FoodItem_To_FoodItemResponse(foodItem))
                .collect(Collectors.toList());

        return CartResponse.builder()
                .cartTotal(customer.getCart().getCartTotal())
                .foodItemResponseList(foodItemResponseList)
                .build();
    }
}
