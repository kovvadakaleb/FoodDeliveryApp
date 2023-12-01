package com.example.FoodDeliveryApp.dto.request;

import com.example.FoodDeliveryApp.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerRequest {


    String name;


    String email;


    String mobileNo;


    Gender gender;


    boolean available;
}
