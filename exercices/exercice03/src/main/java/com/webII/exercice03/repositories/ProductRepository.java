package com.webII.exercice03.repositories;

import com.webII.exercice03.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
