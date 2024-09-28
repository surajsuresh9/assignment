package com.internal_service.service;

import com.internal_service.model.Product;
import com.internal_service.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public List<Product> getProducts(String id, String brand, String category, Integer limit) throws IllegalArgumentException {
        String me = "ProductService.getProducts(): ";
        limit = validateLimit(limit);
        log.info(me + "getting products for shopper={}, brand={}, category={}, limit={}", id, brand, category, limit);
        return productRepo.getProductsByShopper(id, brand, category, limit);
    }

    int validateLimit(Integer limit) throws IllegalArgumentException {
        if (limit != null) {
            if (limit > 100) {
                throw new IllegalArgumentException("max allowed limit is 100");
            }
        } else {
            limit = 10;
        }
        log.info("limit={}", limit);
        return limit;
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }
}
