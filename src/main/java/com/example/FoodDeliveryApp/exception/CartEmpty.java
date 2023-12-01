package com.example.FoodDeliveryApp.exception;

public class CartEmpty extends RuntimeException {
    public CartEmpty(String message) {
        super(message);
    }
}
