package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.request.RestaurantRequest;
import com.example.FoodDeliveryApp.dto.response.MenuResponse;
import com.example.FoodDeliveryApp.dto.response.RestaurantResponse;
import com.example.FoodDeliveryApp.model.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantTransformer {

    public static Restaurant RestaurantRequest_To_Restaurant(RestaurantRequest restaurantRequest){
        return Restaurant.builder()
                .name(restaurantRequest.getName())
                .restaurantCategory(restaurantRequest.getRestaurantCategory())
                .contactNo(restaurantRequest.getContactNo())
                .location(restaurantRequest.getLocation())
                .opened(true)
                .build();
    }

   public static RestaurantResponse Restaurant_To_RestaurantResponse(Restaurant restaurant){

        List<MenuResponse> menuResponseList = restaurant.getMenuItemList()
                .stream()
                .map(menuItem -> MenuTransformer.MenuItem_To_MenuResponse(menuItem))
                .collect(Collectors.toList());

        return RestaurantResponse.builder()
                .contactNo(restaurant.getContactNo())
                .restaurantCategory(restaurant.getRestaurantCategory())
                .location(restaurant.getLocation())
                .menu(menuResponseList)
                .opened(restaurant.isOpened())
                .name(restaurant.getName())
                .build();
   }
}
