package com.example.FoodDeliveryApp.service.Impl;

import com.example.FoodDeliveryApp.dto.response.OrderResponse;
import com.example.FoodDeliveryApp.dto.response.TotalBill;
import com.example.FoodDeliveryApp.exception.CardNotFound;
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
    CouponRepository couponRepository;
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

        DebitCard debitCard = customer.getDebitCard();
        if(debitCard==null){
            throw new CardNotFound("please add Your card for Payment!!!");
        }

        int totalAmount = (int)cart.getCartTotal();
        int gstAmount = (int)(cart.getCartTotal()*((double)5/100));
        Coupon coupon = couponRepository.findrandomCoupon();
        totalAmount =  totalAmount-(int)(cart.getCartTotal()*(coupon.getDiscount()/100));
        totalAmount += gstAmount;


        if(debitCard.getBalance()<totalAmount){
            throw new IndexOutOfBoundsException("Payment Failed -> Insufficient Balance");
        }
        debitCard.setBalance(debitCard.getBalance()-totalAmount);
        OrderEntity orderEntity = OrderTransformer.prepareOrder(cart);
        orderEntity.setPaidAmount(totalAmount);
        OrderEntity savedEntity = orderRepository.save(orderEntity);

        DeliveryPartner deliveryPartner = partnerRepository.findRandomDeliveryBoy();
        while(!deliveryPartner.isAvailable()){
            deliveryPartner = partnerRepository.findRandomDeliveryBoy();
        }
        deliveryPartner.setAvailable(false);
        Restaurant restaurant = cart.getFoodItemList().get(0).getMenuItem().getRestaurant();

        savedEntity.setCoupon(coupon);
        savedEntity.setCustomer(customer);
        savedEntity.setDeliveryPartner(deliveryPartner);
        savedEntity.setFoodItemList(cart.getFoodItemList());
        savedEntity.setRestaurant(restaurant);

        coupon.getOrderEntities().add(savedEntity);
        deliveryPartner.getOrderEntityList().add(savedEntity);
        customer.getOrderEntityList().add(savedEntity);
        restaurant.getOrderEntityList().add(savedEntity);

        int foodAmount = (int)cart.getCartTotal();
        int couponAmount = (int) (cart.getCartTotal()*(coupon.getDiscount()/100));
        for(FoodItem foodItem : cart.getFoodItemList()){
            foodItem.setCart(null);
            foodItem.setOrderEntity(savedEntity);
        }
        cart.setFoodItemList(new ArrayList<>());
        cart.setCartTotal(0);


        customerRepository.save(customer);
        couponRepository.save(coupon);

        OrderResponse orderResponse =  OrderTransformer.OrderEntity_To_OrderResponse(savedEntity);
        TotalBill totalBill = new TotalBill();
        totalBill.setCGST("+"+savedEntity.getCGST());
        totalBill.setSGST("+"+savedEntity.getSGST());
        totalBill.setCouponAmount("-"+couponAmount);
        totalBill.setFoodAmount(foodAmount);
        totalBill.setTotalBillAmount(totalAmount);

        orderResponse.setCouponDiscount(coupon.getDiscount()+"%");
        orderResponse.setTotalBill(totalBill);

        return orderResponse;
    }
}
