package com.internal_service.controller;

import com.internal_service.model.Shopper;
import com.internal_service.service.ShopperService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopper")
@RequiredArgsConstructor
public class ShopperController {

    private final ShopperService shopperService;

    @Timed(value = "getShoppersByProduct.timer", description = "Time taken to process getShoppersByProduct endpoint")
    @GetMapping
    public ResponseEntity<List<Shopper>> getShoppersByProduct(@RequestParam(required = true) String productId,
                                                              @RequestParam(required = false) Integer limit) {
        ResponseEntity response = null;
        try {
            List<String> shoppers = shopperService.getShoppers(productId, limit);
            if (shoppers.isEmpty()) {
                response = new ResponseEntity<>("No Shoppers found for product with id: " + productId, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(shoppers, HttpStatus.OK);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Timed(value = "addShopper.timer", description = "Time taken to process addShopper endpoint")
    @PostMapping
    public ResponseEntity<Shopper> addShopper(@RequestBody Shopper shopper) {
        return new ResponseEntity<Shopper>(shopperService.addShopper(shopper), HttpStatus.CREATED);
    }
}
