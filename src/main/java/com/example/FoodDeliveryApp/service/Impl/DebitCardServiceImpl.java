package com.example.FoodDeliveryApp.service.Impl;

import com.example.FoodDeliveryApp.dto.request.DebitCardRequest;
import com.example.FoodDeliveryApp.exception.CustomerNotFound;
import com.example.FoodDeliveryApp.model.Customer;
import com.example.FoodDeliveryApp.model.DebitCard;
import com.example.FoodDeliveryApp.repository.CustomerRepository;
import com.example.FoodDeliveryApp.repository.DebitCardRepository;
import com.example.FoodDeliveryApp.service.DebitCardService;
import com.example.FoodDeliveryApp.transformer.DebitCardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebitCardServiceImpl implements DebitCardService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    DebitCardRepository debitCardRepository;
    @Override
    public String addDebitCard(DebitCardRequest debitCardRequest) {
        Customer customer = customerRepository.findBymobileNo(debitCardRequest.getCustomerMobileNo());
        if(customer==null){
            throw new CustomerNotFound("customer Not exist!! please SignUp");
        }
        DebitCard debitCard = DebitCardTransformer.DebitCardRequest_To_DebitCard(debitCardRequest);
        debitCard.setCustomer(customer);
        customer.setDebitCard(debitCard);
        debitCardRepository.save(debitCard);
        return "Card Saved Successfully";
    }
}
