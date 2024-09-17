package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers;

import com.matrix.duoc_springboot_ecommerce_pets_ms.application.services.ProductService;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.dto.NewProductDTO;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.Product;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping()
  public ResponseEntity<List<Product>> getAllProducts(
      @RequestParam("limit") Optional<Integer> limit) {
    return limit.isPresent()
        ? ResponseEntity.ok(this.productService.getAllProducts(limit.get()))
        : ResponseEntity.ok(this.productService.getAllProducts());
  }

  @GetMapping("/{productId}")
  public ResponseEntity<Product> getProductById(@PathVariable("productId") String productId) {
    Optional<Product> foundProduct = this.productService.getProductById(productId);
    return foundProduct.isPresent()
        ? ResponseEntity.ok(foundProduct.get())
        : ResponseEntity.notFound().build();
  }

  @GetMapping("/{productId}/stock")
  public ResponseEntity<Integer> getStockByProductId(@PathVariable("productId") String productId) {
    return ResponseEntity.ok(this.productService.getStockOfProductId(productId));
  }

  @PostMapping()
  public ResponseEntity<HashMap<String, String>> createNewProduct(
      @Valid @RequestBody NewProductDTO newProductDTO) {
    String newProductId = this.productService.createNewProduct(newProductDTO);
    HashMap<String, String> response = new HashMap<>();
    response.put("new_product_id", newProductId);

    return ResponseEntity.ok(response);
  }
}
