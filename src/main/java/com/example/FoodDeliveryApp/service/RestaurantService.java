package com.example.FoodDeliveryApp.service;

import com.example.FoodDeliveryApp.dto.request.MenuRequest;
import com.example.FoodDeliveryApp.dto.request.RestaurantRequest;
import com.example.FoodDeliveryApp.dto.response.RestaurantResponse;

public interface RestaurantService {
    RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest);

    RestaurantResponse addMenuToRestaurant(MenuRequest menuRequest);

    String changeStatusOfRestaurant(int id);
}
