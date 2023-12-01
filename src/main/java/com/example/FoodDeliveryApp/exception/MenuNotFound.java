package com.example.FoodDeliveryApp.exception;

public class MenuNotFound extends RuntimeException{
    public MenuNotFound(String message) {
        super(message);
    }
}
