package com.internal_service.repo;

import com.internal_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true, value =
            "SELECT * FROM PRODUCT_TBL p WHERE p.id IN " +
                    "(SELECT product_id FROM SHELF_TBL WHERE shopper_id=:id)" +
                    "AND (:brand is null or p.brand = :brand)" +
                    "AND (:category is null or p.category = :category) limit :limit")
    List<Product> getProductsByShopper(String id, String brand, String category, int limit);
}
