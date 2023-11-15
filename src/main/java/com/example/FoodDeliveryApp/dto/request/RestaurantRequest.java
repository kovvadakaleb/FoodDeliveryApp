package com.example.FoodDeliveryApp.dto.request;

import com.example.FoodDeliveryApp.enums.RestaurantCategory;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantRequest {

    String name;

    RestaurantCategory restaurantCategory;

    String location;

    String contactNo;

}
