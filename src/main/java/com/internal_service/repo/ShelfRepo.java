package com.internal_service.repo;

import com.internal_service.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelfRepo extends JpaRepository<Shelf, Long> {
}
