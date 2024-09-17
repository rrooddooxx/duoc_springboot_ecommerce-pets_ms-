package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.ProductType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
public class NewProductDTO {

  @Enumerated(EnumType.STRING)
  @NotNull
  @JsonProperty("product_type")
  private ProductType productType;

  @NotNull
  @Length(min = 1)
  @JsonProperty("product_brand")
  private String productBrand;

  @NotNull
  @Length(min = 2)
  @JsonProperty("product_name")
  private String productName;

  @NotNull
  @Min(0)
  @JsonProperty("weight_grams")
  private Double weightInGrams;

  @NotNull
  @Min(0)
  @JsonProperty("stock_quantity")
  private int currentStock;

  @NotNull
  @Min(1)
  @JsonProperty("price")
  private BigDecimal price;
}
