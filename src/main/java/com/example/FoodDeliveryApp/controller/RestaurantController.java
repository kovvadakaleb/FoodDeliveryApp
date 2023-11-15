package com.example.FoodDeliveryApp.controller;

import com.example.FoodDeliveryApp.dto.request.FoodItemRequest;
import com.example.FoodDeliveryApp.dto.request.RestaurantRequest;
import com.example.FoodDeliveryApp.dto.response.RestaurantResponse;
import com.example.FoodDeliveryApp.exception.RestaurantNotFound;
import com.example.FoodDeliveryApp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;
    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        RestaurantResponse restaurantResponse = restaurantService.addRestaurant(restaurantRequest);
        return new ResponseEntity(restaurantResponse, HttpStatus.CREATED);
    }

    @PutMapping("/change-status/{id}")
    public ResponseEntity changeStatusOfRestaurant(@PathVariable int id){
        try {
            String message = restaurantService.changeStatusOfRestaurant(id);
            return new ResponseEntity(message,HttpStatus.ACCEPTED);
        }
        catch(RestaurantNotFound e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add-menu")
    public ResponseEntity addMenuToRestaurant(@RequestBody FoodItemRequest foodItemRequest){
        try {
            RestaurantResponse restaurantResponse = restaurantService.addMenuToRestaurant(foodItemRequest);
            return new ResponseEntity(restaurantResponse,HttpStatus.ACCEPTED);
        }
        catch (RestaurantNotFound e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
