package com.example.FoodDeliveryApp.service.Impl;

import com.example.FoodDeliveryApp.dto.response.OrderResponse;
import com.example.FoodDeliveryApp.exception.CartEmpty;
import com.example.FoodDeliveryApp.exception.CustomerNotFound;
import com.example.FoodDeliveryApp.model.*;
import com.example.FoodDeliveryApp.repository.*;
import com.example.FoodDeliveryApp.service.OrderService;
import com.example.FoodDeliveryApp.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
   RestaurantRepository restaurantRepository;
    @Override
    public OrderResponse placeOrder(String customerMobile) {
        Customer customer = customerRepository.findBymobileNo(customerMobile);
        if(customer==null){
            throw new CustomerNotFound("customer not found");
        }
        Cart cart = customer.getCart();
        if(cart.getFoodItemList().size()==0){
            throw new CartEmpty("cart is empty");
        }
        OrderEntity orderEntity = OrderTransformer.prepareOrder(cart);
        OrderEntity savedEntity = orderRepository.save(orderEntity);

        DeliveryPartner deliveryPartner = partnerRepository.findRandomDeliveryBoy();
        while(!deliveryPartner.isAvailable()){
            deliveryPartner = partnerRepository.findRandomDeliveryBoy();
        }
        deliveryPartner.setAvailable(false);
        Restaurant restaurant = cart.getFoodItemList().get(0).getMenuItem().getRestaurant();

        savedEntity.setCustomer(customer);
        savedEntity.setDeliveryPartner(deliveryPartner);
        savedEntity.setFoodItemList(cart.getFoodItemList());
        savedEntity.setRestaurant(restaurant);

        deliveryPartner.getOrderEntityList().add(savedEntity);
        customer.getOrderEntityList().add(savedEntity);
        restaurant.getOrderEntityList().add(savedEntity);

        for(FoodItem foodItem : cart.getFoodItemList()){
            foodItem.setCart(null);
            foodItem.setOrderEntity(savedEntity);
        }
        cart.setFoodItemList(new ArrayList<>());
        cart.setCartTotal(0);


        partnerRepository.save(deliveryPartner);
        customerRepository.save(customer);
        restaurantRepository.save(restaurant);


        return OrderTransformer.OrderEntity_To_OrderResponse(savedEntity);
    }
}
