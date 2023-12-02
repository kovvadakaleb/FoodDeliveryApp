package com.example.FoodDeliveryApp.controller;

import com.example.FoodDeliveryApp.dto.request.DebitCardRequest;
import com.example.FoodDeliveryApp.exception.CustomerNotFound;
import com.example.FoodDeliveryApp.service.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("debit_card")
public class DebitCardController {

    @Autowired
    DebitCardService debitCardService;
    @PostMapping("/add")
    public ResponseEntity addDebitCard(@RequestBody DebitCardRequest debitCardRequest){
        try {
            String response = debitCardService.addDebitCard(debitCardRequest);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch(CustomerNotFound e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
