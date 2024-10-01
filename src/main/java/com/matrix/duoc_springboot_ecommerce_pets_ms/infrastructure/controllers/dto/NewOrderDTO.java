package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.OrderStatus;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.PaymentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewOrderDTO {
  @JsonProperty("client_id")
  @Min(1)
  private Long clientId;

  @Enumerated(EnumType.STRING)
  @JsonProperty("order_status")
  private OrderStatus orderStatus;

  @JsonProperty("order_submit_date")
  private LocalDateTime orderSubmittedDate;

  @Enumerated(EnumType.STRING)
  @JsonProperty("payment_type")
  private PaymentType paymentType;
}
