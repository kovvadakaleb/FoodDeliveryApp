package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.request.CustomerRequest;
import com.example.FoodDeliveryApp.dto.response.CartResponse;
import com.example.FoodDeliveryApp.dto.response.CustomerResponse;
import com.example.FoodDeliveryApp.dto.response.FoodItemResponse;
import com.example.FoodDeliveryApp.model.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerTransformer {

    public static Customer CustomerRequest_To_Customer(CustomerRequest customerRequest){
        return Customer.builder()
                .name(customerRequest.getName())
                .mobileNo(customerRequest.getMobileNo())
                .email(customerRequest.getEmail())
                .address(customerRequest.getAddress())
                .gender(customerRequest.getGender())
                .build();
    }

    public static CustomerResponse Customer_To_CustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .name(customer.getName())
                .address(customer.getAddress())
                .gender(customer.getGender())
                .mobileNo(customer.getMobileNo())
                .cartResponse(CartTransformer.Cart_To_CartResponse(customer))
                .build();
    }

}
