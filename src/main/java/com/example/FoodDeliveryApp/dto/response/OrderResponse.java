package com.example.FoodDeliveryApp.dto.response;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    String orderId;

    Date date;

    double price;

    String customerName;

    String customerMobileNo;

    String deliveryName;

    String deliveryBoyMobile;

    String restaurantName;

    List<FoodResponse> foodResponseList;

}
