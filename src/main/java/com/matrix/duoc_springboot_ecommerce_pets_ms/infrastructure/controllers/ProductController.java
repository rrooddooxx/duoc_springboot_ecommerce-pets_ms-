package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers;

import com.matrix.duoc_springboot_ecommerce_pets_ms.application.services.ProductService;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.dto.NewProductDTO;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.mapper.ProductControllerMapper;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.Product;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

  private final ProductService productService;
  private final ProductControllerMapper resMapper;

  @GetMapping()
  public ResponseEntity<CollectionModel<EntityModel<Product>>> getAllProducts() {
    return ResponseEntity.ok(
        resMapper.mapListToCollection(
            resMapper.mapListToEntities(this.productService.getAllProducts())));
  }

  @GetMapping("/{productId}")
  public ResponseEntity<EntityModel<Product>> getProductById(
      @PathVariable("productId") String productId) {
    Optional<Product> foundProduct = this.productService.getProductById(productId);
    return foundProduct.isPresent()
        ? ResponseEntity.ok(resMapper.mapDomainToEntityModel(foundProduct.get()))
        : ResponseEntity.notFound().build();
  }

  @GetMapping("/{productId}/stock")
  public ResponseEntity<EntityModel<Map<String, Integer>>> getStockByProductId(
      @PathVariable("productId") String productId) {
    return ResponseEntity.ok(
        resMapper.mapProductStockToEntityModel(
            this.productService.getStockOfProductId(productId), productId));
  }

  @PostMapping()
  public ResponseEntity<EntityModel<Map<String, String>>> createNewProduct(
      @Valid @RequestBody NewProductDTO newProductDTO) {
    String newProductId = this.productService.createNewProduct(newProductDTO);
    return ResponseEntity.ok(resMapper.mapNewProductToEntityModel(newProductId));
  }
}
