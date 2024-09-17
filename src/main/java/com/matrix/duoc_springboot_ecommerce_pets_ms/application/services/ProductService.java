package com.matrix.duoc_springboot_ecommerce_pets_ms.application.services;

import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.dto.NewProductDTO;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.ProductRepository;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.Product;

import java.lang.module.ResolutionException;
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
    return this.productRepository.findAll();
  }

  public List<Product> getAllProducts() {
    return this.productRepository.findAll();
  }

  public Optional<Product> getProductById(String productId) {
    return this.productRepository.findById(productId);
  }

  public Integer getStockOfProductId(String productId) {
    Optional<Product> product = this.productRepository.findById(productId);
    if (product.isEmpty()) {
      throw new ResolutionException(
          String.format("Producto con id (%s) no existe en sistema.", productId));
    }
    return product.map(Product::getCurrentStock).orElse(0);
  }

  public String createNewProduct(NewProductDTO newProductDTO) {
    Product newProduct =
        this.productRepository.save(
            Product.builder()
                .productBrand(newProductDTO.getProductBrand())
                .productName(newProductDTO.getProductName())
                .productType(newProductDTO.getProductType())
                .price(newProductDTO.getPrice())
                .currentStock(newProductDTO.getCurrentStock())
                .weightInGrams(newProductDTO.getWeightInGrams())
                .build());

    return newProduct.getProductId();
  }
}
