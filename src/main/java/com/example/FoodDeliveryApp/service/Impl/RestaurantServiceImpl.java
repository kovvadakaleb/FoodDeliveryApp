package com.example.FoodDeliveryApp.service.Impl;

import com.example.FoodDeliveryApp.dto.request.FoodItemRequest;
import com.example.FoodDeliveryApp.dto.request.RestaurantRequest;
import com.example.FoodDeliveryApp.dto.response.RestaurantResponse;
import com.example.FoodDeliveryApp.exception.RestaurantNotFound;
import com.example.FoodDeliveryApp.model.FoodItem;
import com.example.FoodDeliveryApp.model.Restaurant;
import com.example.FoodDeliveryApp.repository.RestaurantRepository;
import com.example.FoodDeliveryApp.service.RestaurantService;
import com.example.FoodDeliveryApp.transformer.FoodItemsTransformer;
import com.example.FoodDeliveryApp.transformer.RestaurantTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;


    @Override
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = RestaurantTransformer.RestaurantRequest_To_Restaurant(restaurantRequest);
        restaurant.setFoodItemList(new ArrayList<>());
        restaurant.setOrderEntityList(new ArrayList<>());

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantTransformer.Restaurant_To_RestaurantResponse(savedRestaurant);
    }

    @Override
    public RestaurantResponse addMenuToRestaurant(FoodItemRequest foodItemRequest) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(foodItemRequest.getRestaurantID());
        if(optionalRestaurant.isEmpty()){
            throw new RestaurantNotFound("Invalid Restaurant ID");
        }
        Restaurant restaurant = optionalRestaurant.get();
        FoodItem foodItem = FoodItemsTransformer.FoodItemRequest_To_FoodItem(foodItemRequest);
        foodItem.setRestaurant(restaurant);

        restaurant.getFoodItemList().add(foodItem);

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return RestaurantTransformer.Restaurant_To_RestaurantResponse(savedRestaurant);
    }

    @Override
    public String changeStatusOfRestaurant(int id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if(optionalRestaurant.isEmpty()){
            throw new RestaurantNotFound("Invalid Restaurant ID");
        }
        Restaurant restaurant = optionalRestaurant.get();
        restaurant.setOpened(!restaurant.isOpened());
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        if(savedRestaurant.isOpened()){
            return "Restaurant is opened now";
        }
        else{
            return "Restaurant is closed now";
        }
    }
}
