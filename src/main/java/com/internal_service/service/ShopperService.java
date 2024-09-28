package com.internal_service.service;

import com.internal_service.model.Shopper;
import com.internal_service.repo.ShopperRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShopperService {

    private final ShopperRepo shopperRepo;

    public List<String> getShoppers(String id, Integer limit) {
        String me = "ShopperService.getShoppers(): ";
        limit = validateLimit(limit);
        log.info(me + "getting Shoppers for productId={}, limit={}", id, limit);
        return shopperRepo.getShoppers(id, limit);
    }

    Integer validateLimit(Integer limit) {
        if (limit != null) {
            if (limit > 1000) {
                throw new IllegalArgumentException("max limit allowed is 1000");
            }
        } else {
            limit = 10;
        }
        log.info("limit={}", limit);
        return limit;
    }

    public Shopper addShopper(Shopper shopper) {
        return shopperRepo.save(shopper);
    }
}
