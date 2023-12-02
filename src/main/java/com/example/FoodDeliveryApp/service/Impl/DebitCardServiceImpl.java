package com.example.FoodDeliveryApp.service.Impl;

import com.example.FoodDeliveryApp.dto.request.DebitCardRequest;
import com.example.FoodDeliveryApp.model.DebitCard;
import com.example.FoodDeliveryApp.repository.DebitCardRepository;
import com.example.FoodDeliveryApp.service.DebitCardService;
import com.example.FoodDeliveryApp.transformer.DebitCardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebitCardServiceImpl implements DebitCardService {

    @Autowired
    DebitCardRepository debitCardRepository;
    @Override
    public String addDebitCard(DebitCardRequest debitCardRequest) {
        DebitCard debitCard = DebitCardTransformer.DebitCardRequest_To_DebitCard(debitCardRequest);
        debitCardRepository.save(debitCard);
        return "Card Saved Successfully";
    }
}
