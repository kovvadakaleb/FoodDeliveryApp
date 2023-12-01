package com.example.FoodDeliveryApp.service.Impl;

import com.example.FoodDeliveryApp.dto.request.PartnerRequest;
import com.example.FoodDeliveryApp.model.DeliveryPartner;
import com.example.FoodDeliveryApp.repository.PartnerRepository;
import com.example.FoodDeliveryApp.service.PartnerService;
import com.example.FoodDeliveryApp.transformer.PartnerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    PartnerRepository partnerRepository;
    @Override
    public String addDeliveryPartner(PartnerRequest partnerRequest) {
        DeliveryPartner deliveryPartner = PartnerTransformer.PartnerRequest_To_DeliveryPartner(partnerRequest);
        partnerRepository.save(deliveryPartner);
        return "Delivery Boy Added Successfully";
    }
}
