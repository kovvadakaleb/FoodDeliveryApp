package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.request.PartnerRequest;
import com.example.FoodDeliveryApp.model.DeliveryPartner;

import java.util.ArrayList;

public class PartnerTransformer {

    public static DeliveryPartner PartnerRequest_To_DeliveryPartner(PartnerRequest partnerRequest){
        return DeliveryPartner.builder()
                .name(partnerRequest.getName())
                .email(partnerRequest.getEmail())
                .mobileNo(partnerRequest.getMobileNo())
                .gender(partnerRequest.getGender())
                .available(partnerRequest.isAvailable())
                .orderEntityList(new ArrayList<>())
                .build();
    }
}
