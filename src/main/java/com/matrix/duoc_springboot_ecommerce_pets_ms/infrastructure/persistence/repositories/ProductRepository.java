package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories;

import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {}
