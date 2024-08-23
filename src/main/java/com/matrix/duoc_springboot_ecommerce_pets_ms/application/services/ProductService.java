package com.matrix.duoc_springboot_ecommerce_pets_ms.application.services;

import com.matrix.duoc_springboot_ecommerce_pets_ms.domain.Product;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  public List<Product> getAllProducts(Integer limit) {
    return this.productRepository.getProducts(limit);
  }

  public List<Product> getAllProducts() {
    return this.productRepository.getAllProducts();
  }

  public Optional<Product> getProductById(String productId) {
    return this.productRepository.getAllProducts().stream()
        .filter(product -> product.getProductId().equals(productId))
        .findFirst();
  }

  public Integer getStockOfProductId(String productId) {
    Optional<Product> product =
        this.productRepository.getAllProducts().stream()
            .filter(p -> p.getProductId().equals(productId))
            .findFirst();

    return product.isPresent() ? product.get().getCurrentStock() : 0;
  }
}
