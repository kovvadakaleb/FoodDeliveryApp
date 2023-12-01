package com.example.FoodDeliveryApp.controller;

import com.example.FoodDeliveryApp.dto.request.PartnerRequest;
import com.example.FoodDeliveryApp.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partner")
public class PartnerController {

    @Autowired
    PartnerService partnerService;
    @PostMapping("/add")
    public ResponseEntity addDeliveryPartner(@RequestBody  PartnerRequest partnerRequest){
        String response = partnerService.addDeliveryPartner(partnerRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
