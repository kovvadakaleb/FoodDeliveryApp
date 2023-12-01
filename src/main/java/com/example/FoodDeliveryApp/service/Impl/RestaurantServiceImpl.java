package com.example.FoodDeliveryApp.service.Impl;

import com.example.FoodDeliveryApp.dto.request.MenuRequest;
import com.example.FoodDeliveryApp.dto.request.RestaurantRequest;
import com.example.FoodDeliveryApp.dto.response.RestaurantResponse;
import com.example.FoodDeliveryApp.exception.RestaurantNotFound;
import com.example.FoodDeliveryApp.model.MenuItem;
import com.example.FoodDeliveryApp.model.Restaurant;
import com.example.FoodDeliveryApp.repository.RestaurantRepository;
import com.example.FoodDeliveryApp.service.RestaurantService;
import com.example.FoodDeliveryApp.transformer.MenuTransformer;
import com.example.FoodDeliveryApp.transformer.RestaurantTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;


    @Override
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = RestaurantTransformer.RestaurantRequest_To_Restaurant(restaurantRequest);
        restaurant.setMenuItemList(new ArrayList<>());
        restaurant.setOrderEntityList(new ArrayList<>());

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantTransformer.Restaurant_To_RestaurantResponse(savedRestaurant);
    }

    @Override
    public RestaurantResponse addMenuToRestaurant(MenuRequest menuRequest) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(menuRequest.getRestaurantID());
        if(optionalRestaurant.isEmpty()){
            throw new RestaurantNotFound("Invalid Restaurant ID");
        }
        Restaurant restaurant = optionalRestaurant.get();
        MenuItem menuItem = MenuTransformer.MenuRequest_To_MenuItem(menuRequest);
        menuItem.setRestaurant(restaurant);

        restaurant.getMenuItemList().add(menuItem);

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
