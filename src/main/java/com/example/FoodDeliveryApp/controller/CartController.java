package com.example.FoodDeliveryApp.controller;

import com.example.FoodDeliveryApp.dto.request.FoodRequest;
import com.example.FoodDeliveryApp.dto.response.CartStatusResponse;
import com.example.FoodDeliveryApp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;
    @PostMapping("/add-Foods")
    public ResponseEntity addFoodItemsToCart(@RequestBody FoodRequest foodRequest){
        try {
            CartStatusResponse cartStatusResponse = cartService.addFoodItemsToCart(foodRequest);
            return new ResponseEntity(cartStatusResponse, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
