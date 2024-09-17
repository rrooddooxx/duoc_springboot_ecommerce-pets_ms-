package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ECOM_PRODUCTS", schema = "PETSECOMMERCE_MS")
public class Product {

  @GeneratedValue(strategy = GenerationType.UUID)
  @Id
  private String productId;

  @Enumerated(EnumType.STRING)
  @NotNull
  @Column(name = "product_type")
  private ProductType productType;

  @Column(name = "product_brand")
  @NotNull
  @Length(min = 1)
  private String productBrand;

  @Column(name = "product_name")
  @NotNull
  @Length(min = 2)
  private String productName;

  @Column(name = "weight_grams")
  @NotNull
  @Min(0)
  private Double weightInGrams;

  @Column(name = "stock_quantity")
  @NotNull
  @Min(0)
  private int currentStock;

  @Column(name = "price")
  @NotNull
  @Min(1)
  private BigDecimal price;
}
