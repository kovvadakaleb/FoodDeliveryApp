package com.example.FoodDeliveryApp.exception;

import org.springframework.data.jpa.repository.JpaRepository;

public class RestaurantNotFound extends RuntimeException {
    public RestaurantNotFound(String message) {
        super(message);
    }
}
