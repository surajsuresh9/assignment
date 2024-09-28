package com.internal_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "product_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @Column(name = "id")
    private String productId;
    @Column(name = "category")
    private String category;
    @Column(name = "brand")
    private String brand;
}
