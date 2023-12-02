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
                .foodBill(cart.getCartTotal())
                .CGST(cart.getCartTotal()*((double)3/100))
                .SGST(cart.getCartTotal()*((double)2/100))
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
                .centralGST("3%")
                .stateGST("2%")
                .customerMobileNo(orderEntity.getCustomer().getMobileNo())
                .customerName(orderEntity.getCustomer().getName())
                .deliveryName(orderEntity.getDeliveryPartner().getName())
                .deliveryBoyMobile(orderEntity.getDeliveryPartner().getMobileNo())
                .restaurantName(orderEntity.getRestaurant().getName())
                .foodResponseList(list)
                .build();
    }

}

