package com.example.FoodDeliveryApp.service.Impl;

import com.example.FoodDeliveryApp.dto.request.FoodRequest;
import com.example.FoodDeliveryApp.dto.response.CartStatusResponse;
import com.example.FoodDeliveryApp.dto.response.FoodResponse;
import com.example.FoodDeliveryApp.exception.CustomerNotFound;
import com.example.FoodDeliveryApp.exception.MenuNotFound;
import com.example.FoodDeliveryApp.exception.OutOfStock;
import com.example.FoodDeliveryApp.exception.RestaurantNotFound;
import com.example.FoodDeliveryApp.model.Cart;
import com.example.FoodDeliveryApp.model.Customer;
import com.example.FoodDeliveryApp.model.FoodItem;
import com.example.FoodDeliveryApp.model.MenuItem;
import com.example.FoodDeliveryApp.repository.CartRepository;
import com.example.FoodDeliveryApp.repository.CustomerRepository;
import com.example.FoodDeliveryApp.repository.FoodRepository;
import com.example.FoodDeliveryApp.repository.MenuRepository;
import com.example.FoodDeliveryApp.service.CartService;
import com.example.FoodDeliveryApp.transformer.CartTransformer;
import com.example.FoodDeliveryApp.transformer.FoodTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public CartStatusResponse addFoodItemsToCart(FoodRequest foodRequest) {
        Customer customer = customerRepository.findBymobileNo(foodRequest.getCustomerMobile());
        if (customer == null) {
            throw new CustomerNotFound("Invalid MobileNo");
        }

        Optional<MenuItem> menuItem = menuRepository.findById(foodRequest.getMenuId());
        if (menuItem.isEmpty()) {
            throw new MenuNotFound("Item Not Available in restaurant");
        }

        MenuItem item = menuItem.get();
        if (!item.getRestaurant().isOpened()) {
            throw new RestaurantNotFound("restaurant is closed");
        }

        if (!item.isAvailable()) {
            throw new OutOfStock("Dish is out of Stock");
        }

        Cart cart = customer.getCart();
        boolean addedFood = false;
        boolean differentRestaurant = false;
        for (FoodItem food : cart.getFoodItemList()) {
            if (food.getMenuItem().getId() == item.getId()) {
                addedFood = true;
                food.setRequiredQuantity(food.getRequiredQuantity() + foodRequest.getRequiredQuantity());
                food.setTotalCost(food.getTotalCost() + (foodRequest.getRequiredQuantity() * item.getPrice()));
                break;
            }
            else if (food.getMenuItem().getRestaurant().getId() != item.getRestaurant().getId()) {
                differentRestaurant = true;
                break;
            }
        }

        if (addedFood == false) {

            if (differentRestaurant == true) {
                for (FoodItem foodItem : cart.getFoodItemList()) {
                    foodRepository.foodDelete(foodItem.getId());
                }
                cart.getFoodItemList().clear();
            }
            FoodItem foodItem = FoodItem.builder()
                .menuItem(item)
                .requiredQuantity(foodRequest.getRequiredQuantity())
                .totalCost(item.getPrice() * foodRequest.getRequiredQuantity())
                .build();

        FoodItem savedFoodItem = foodRepository.save(foodItem);
        savedFoodItem.setCart(cart);
        item.getFoodItemList().add(savedFoodItem);
        cart.getFoodItemList().add(savedFoodItem);
        }

        double cartTotal = 0;
        for(FoodItem foodItem1 : cart.getFoodItemList()) {
            cartTotal += foodItem1.getTotalCost();
        }

        cart.setCartTotal(cartTotal);
        Cart savedcart = cartRepository.save(cart);
        menuRepository.save(item);

        return CartTransformer.Cart_To_CartStatusResponse(customer);
    }
}
