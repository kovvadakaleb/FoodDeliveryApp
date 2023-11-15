package com.example.FoodDeliveryApp.dto.request;

import com.example.FoodDeliveryApp.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {

    String name;


    String mobileNo;


    String email;


    String address;


    Gender gender;
}
