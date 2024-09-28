package com.internal_service.repo;

import com.internal_service.model.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopperRepo extends JpaRepository<Shopper, Long> {
    @Query(nativeQuery = true, value =
            "SELECT shopper_id FROM SHOPPER_TBL s WHERE s.shopper_id IN " +
                    "(SELECT shopper_id FROM SHELF_TBL sh WHERE sh.product_id=:id) limit :limit")
    List<String> getShoppers(String id, Integer limit);
}
