package com.example.FoodDeliveryApp.exception;

public class OutOfStock extends RuntimeException{
    public OutOfStock(String message) {
        super(message);
    }
}
