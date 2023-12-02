package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.request.DebitCardRequest;
import com.example.FoodDeliveryApp.model.DebitCard;

public class DebitCardTransformer {

    public static DebitCard DebitCardRequest_To_DebitCard(DebitCardRequest debitCardRequest){
        return DebitCard.builder()
                .cardStatus(debitCardRequest.getCardStatus())
                .cardNo(debitCardRequest.getCardNo())
                .cardCvv(debitCardRequest.getCardCvv())
                .balance(debitCardRequest.getBalance())
                .build();
    }
}
