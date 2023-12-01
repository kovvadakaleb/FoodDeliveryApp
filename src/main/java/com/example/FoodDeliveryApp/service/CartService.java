package com.example.FoodDeliveryApp.service;

import com.example.FoodDeliveryApp.dto.request.FoodRequest;
import com.example.FoodDeliveryApp.dto.response.CartStatusResponse;

public interface CartService {
    CartStatusResponse addFoodItemsToCart(FoodRequest foodRequest);
}
