package com.example.FoodDeliveryApp.dto.response;

import com.example.FoodDeliveryApp.enums.RestaurantCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponse {

    String name;

    RestaurantCategory restaurantCategory;

    String location;

    String contactNo;

    boolean opened;

    List<MenuResponse> menu;
}
