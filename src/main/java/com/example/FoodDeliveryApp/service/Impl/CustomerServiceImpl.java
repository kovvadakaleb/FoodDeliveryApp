package com.example.FoodDeliveryApp.service.Impl;

import com.example.FoodDeliveryApp.dto.request.CustomerRequest;
import com.example.FoodDeliveryApp.dto.response.CustomerResponse;
import com.example.FoodDeliveryApp.exception.CustomerNotFound;
import com.example.FoodDeliveryApp.model.Cart;
import com.example.FoodDeliveryApp.model.Customer;
import com.example.FoodDeliveryApp.repository.CustomerRepository;
import com.example.FoodDeliveryApp.service.CustomerService;
import com.example.FoodDeliveryApp.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        Customer customer = CustomerTransformer.CustomerRequest_To_Customer(customerRequest);
        //Allocate cart to customer
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setFoodItemList(new ArrayList<>());

        customer.setOrderEntityList(new ArrayList<>());
        customer.setCart(cart);
        cart.setCustomer(customer);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerTransformer.Customer_To_CustomerResponse(savedCustomer);
    }

    @Override
    public CustomerResponse getCustomer(String mobileNo) {
        Optional<Customer> optionalCustomer = Optional.ofNullable(customerRepository.findBymobileNo(mobileNo));
        if(optionalCustomer.isEmpty()){
           throw new CustomerNotFound("Invalid customer mobileNo");
        }
        return CustomerTransformer.Customer_To_CustomerResponse(optionalCustomer.get());
    }
}
