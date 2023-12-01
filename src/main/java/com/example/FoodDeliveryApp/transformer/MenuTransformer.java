package com.example.FoodDeliveryApp.transformer;

import com.example.FoodDeliveryApp.dto.request.MenuRequest;
import com.example.FoodDeliveryApp.dto.response.MenuResponse;
import com.example.FoodDeliveryApp.model.MenuItem;

public class MenuTransformer {

    public static MenuResponse MenuItem_To_MenuResponse(MenuItem menuItem){
        return MenuResponse.builder()
                .foodCategory(menuItem.getFoodCategory())
                .veg(menuItem.isVeg())
                .price(menuItem.getPrice())
                .dishName(menuItem.getDishName())
                .build();
    }

   public static MenuItem MenuRequest_To_MenuItem(MenuRequest menuRequest){
        return MenuItem.builder()
                .foodCategory(menuRequest.getFoodCategory())
                .price(menuRequest.getPrice())
                .veg(menuRequest.isVeg())
                .available(true)
                .dishName(menuRequest.getDishName())
                .build();
   }
}
