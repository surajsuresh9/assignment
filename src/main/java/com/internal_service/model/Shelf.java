package com.internal_service.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shelf_tbl")
@Builder
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private double relevancyScore;

    public Shelf(String productId, double relevancyScore) {
        this.productId = productId;
        this.relevancyScore = relevancyScore;
    }
}
