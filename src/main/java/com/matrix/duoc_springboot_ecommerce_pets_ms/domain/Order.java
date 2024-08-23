package com.matrix.duoc_springboot_ecommerce_pets_ms.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  private String orderId;
  private String clientId;
  private List<Product> products;
  private LocalDateTime orderSubmittedDate;
  private PaymentType paymentType;
  private BigDecimal totalAmount;
}
