package com.example.FoodDeliveryApp.exception;

public class InsufficientBalance extends RuntimeException{
    public InsufficientBalance(String message) {
        super(message);
    }
}
