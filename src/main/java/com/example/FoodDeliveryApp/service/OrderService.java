package com.example.FoodDeliveryApp.service;

import com.example.FoodDeliveryApp.dto.response.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(String customerMobile);
}
