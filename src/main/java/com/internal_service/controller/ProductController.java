package com.internal_service.controller;

import com.internal_service.model.Product;
import com.internal_service.service.ProductService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Timed(value = "getProductsByShopper.timer", description = "Time taken to process getProductsByShopper endpoint")
    @GetMapping
    public ResponseEntity<List<Product>> getProductsByShopper(@RequestParam(required = true, value = "shopperId") String id,
                                                              @RequestParam(required = false, value = "brand") String brand,
                                                              @RequestParam(required = false, value = "category") String category,
                                                              @RequestParam(required = false, value = "limit") Integer limit
    ) {
        ResponseEntity response = null;
        try {
            List<Product> products = productService.getProducts(id, brand, category, limit);
            if (products.isEmpty()) {
                response = new ResponseEntity("No products found for given criteria", HttpStatus.OK);
            } else {
                response = new ResponseEntity<List<Product>>(products, HttpStatus.OK);
            }
        } catch (Exception e) {
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Timed(value = "addProduct.timer", description = "Time taken to process addProduct endpoint")
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.CREATED);
    }
}