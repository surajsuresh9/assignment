package com.internal_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopper_tbl")
@Builder
public class Shopper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "shopper_id")
    private String shopperId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopper_id", referencedColumnName = "shopper_id")
    private List<Shelf> shelf;

    public Shopper(String shopperId, List<Shelf> shelf) {
        this.shopperId = shopperId;
        this.shelf = shelf;
    }
}
