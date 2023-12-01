package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.response.CartResponse;
import com.example.FoodDeliveryApp.dto.response.CartStatusResponse;
import com.example.FoodDeliveryApp.dto.response.FoodResponse;
import com.example.FoodDeliveryApp.dto.response.MenuResponse;
import com.example.FoodDeliveryApp.model.Cart;
import com.example.FoodDeliveryApp.model.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CartTransformer {

    public static CartResponse Cart_To_CartResponse(Customer customer){
        return CartResponse.builder()
                .cartTotal(customer.getCart().getCartTotal())
                .foodItems(FoodTransformer.PrepareListOfFoodResponse_From_FoodItem(customer))
                .build();
    }

    public static CartStatusResponse Cart_To_CartStatusResponse(Customer customer){
        return CartStatusResponse.builder()
                .customerName(customer.getName())
                .address(customer.getAddress())
                .customerMobile(customer.getMobileNo())
                .cartTotal(customer.getCart().getCartTotal())
                .foodResponseList(FoodTransformer.PrepareListOfFoodResponse_From_FoodItem(customer))
                .restaurantName(customer.getCart().getFoodItemList().get(0).getMenuItem().getRestaurant().getName())
                .build();
    }
}
