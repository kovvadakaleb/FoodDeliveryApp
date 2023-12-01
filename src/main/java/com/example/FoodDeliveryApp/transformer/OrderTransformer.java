package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.response.FoodResponse;
import com.example.FoodDeliveryApp.dto.response.OrderResponse;
import com.example.FoodDeliveryApp.model.Cart;
import com.example.FoodDeliveryApp.model.OrderEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderTransformer {

    public static OrderEntity prepareOrder(Cart cart){
        return OrderEntity.builder()
                .orderId(String.valueOf(UUID.randomUUID()))
                .price(cart.getCartTotal())
                .build();
    }

    public static OrderResponse OrderEntity_To_OrderResponse(OrderEntity orderEntity){

        List<FoodResponse> list = orderEntity.getFoodItemList()
                .stream()
                .map(foodItem -> FoodTransformer.FoodItem_To_FoodResponse(foodItem))
                .collect(Collectors.toList());

        return OrderResponse.builder()
                .orderId(orderEntity.getOrderId())
                .date(orderEntity.getDate())
                .price(orderEntity.getPrice())
                .customerMobileNo(orderEntity.getCustomer().getMobileNo())
                .customerName(orderEntity.getCustomer().getName())
                .deliveryName(orderEntity.getDeliveryPartner().getName())
                .deliveryBoyMobile(orderEntity.getDeliveryPartner().getMobileNo())
                .restaurantName(orderEntity.getRestaurant().getName())
                .foodResponseList(list)
                .build();
    }

}

