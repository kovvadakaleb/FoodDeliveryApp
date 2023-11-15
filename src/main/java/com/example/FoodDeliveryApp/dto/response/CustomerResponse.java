package com.example.FoodDeliveryApp.dto.response;

import com.example.FoodDeliveryApp.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    String name;


    String mobileNo;


    String address;


    Gender gender;

    CartResponse cartResponse;
}
