package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ECOM_ORDERS", schema = "PETSECOMMERCE_MS")
public class Order {

  @GeneratedValue(strategy = GenerationType.UUID)
  @Id
  private String orderId;

  @ManyToOne
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Enumerated(EnumType.STRING)
  @Column(name = "order_status")
  private OrderStatus orderStatus;

  @ElementCollection
  @Column(name = "productids_in_order")
  private List<String> productIdsInOrder;

  @Column(name = "order_submit_date")
  private LocalDateTime orderSubmittedDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_type")
  private PaymentType paymentType;

  @Column(name = "total_amount")
  private BigDecimal totalAmount;
}
