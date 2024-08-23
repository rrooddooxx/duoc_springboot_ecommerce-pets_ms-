package com.matrix.duoc_springboot_ecommerce_pets_ms.domain;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  private String productId;
  private ProductType productType;
  private String productBrand;
  private String productName;
  private Double weightInGrams;
  private StockStatus stockStatus;
  private int currentStock;
  private BigDecimal price;

  public StockStatus getStockStatus() {
    if (currentStock <= 0) {
      this.stockStatus = StockStatus.UNAVAILABLE;
    } else {
      this.stockStatus = StockStatus.AVAILABLE;
    }
    return this.stockStatus;
  }
}
