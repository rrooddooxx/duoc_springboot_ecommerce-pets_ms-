package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderStatusDTO {
  @JsonProperty("status")
  @Enumerated(EnumType.STRING)
  @NotNull
  private OrderStatus status;
}
