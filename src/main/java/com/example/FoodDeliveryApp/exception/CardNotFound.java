package com.example.FoodDeliveryApp.exception;

public class CardNotFound extends RuntimeException{
    public CardNotFound(String message) {
        super(message);
    }
}
