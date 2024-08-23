package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories;

import com.matrix.duoc_springboot_ecommerce_pets_ms.domain.Client;
import com.matrix.duoc_springboot_ecommerce_pets_ms.domain.Order;
import com.matrix.duoc_springboot_ecommerce_pets_ms.domain.OrderStatus;
import com.matrix.duoc_springboot_ecommerce_pets_ms.domain.PaymentType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderRepository {
  private final ArrayList<Order> orders = new ArrayList<>();
  private final ClientRepository clientRepository;

  public OrderRepository(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
    this.loadData();
  }

  private String generateBookingUuid() {
    return UUID.randomUUID().toString();
  }

  private void loadData() {
    try {
      List<Client> clients = this.clientRepository.getAllClients();
      orders.add(
          Order.builder()
              .orderId(this.generateBookingUuid())
              .clientId(clients.getFirst().getClientId())
              .orderStatus(OrderStatus.CANCELLED)
              .productsInBasketIds(Arrays.asList("P001", "P003"))
              .orderSubmittedDate(LocalDateTime.of(2024, 8, 1, 14, 30))
              .paymentType(PaymentType.CREDIT)
              .totalAmount(new BigDecimal("16980.00"))
              .build());

      orders.add(
          Order.builder()
              .orderId(this.generateBookingUuid())
              .clientId(clients.get(1).getClientId())
              .orderStatus(OrderStatus.CANCELLED)
              .productsInBasketIds(Arrays.asList("P004", "P005"))
              .orderSubmittedDate(LocalDateTime.of(2024, 8, 2, 10, 0))
              .paymentType(PaymentType.DEBIT)
              .totalAmount(new BigDecimal("18980.00"))
              .build());

      orders.add(
          Order.builder()
              .orderId(this.generateBookingUuid())
              .clientId(clients.get(2).getClientId())
              .orderStatus(OrderStatus.PENDING)
              .productsInBasketIds(List.of("P007"))
              .orderSubmittedDate(LocalDateTime.of(2024, 8, 3, 16, 45))
              .paymentType(PaymentType.CASH)
              .totalAmount(new BigDecimal("17990.00"))
              .build());

      orders.add(
          Order.builder()
              .orderId(this.generateBookingUuid())
              .clientId(clients.get(3).getClientId())
              .orderStatus(OrderStatus.DELIVERED)
              .productsInBasketIds(Arrays.asList("P009", "P010"))
              .orderSubmittedDate(LocalDateTime.of(2024, 8, 4, 12, 15))
              .paymentType(PaymentType.UPFRONT)
              .totalAmount(new BigDecimal("27980.00"))
              .build());

      orders.add(
          Order.builder()
              .orderId(this.generateBookingUuid())
              .clientId(clients.get(4).getClientId())
              .orderStatus(OrderStatus.CONFIRMED)
              .productsInBasketIds(Arrays.asList("P011", "P013"))
              .orderSubmittedDate(LocalDateTime.of(2024, 8, 5, 9, 30))
              .paymentType(PaymentType.DEBIT)
              .totalAmount(new BigDecimal("16580.00"))
              .build());

      orders.add(
          Order.builder()
              .orderId(this.generateBookingUuid())
              .clientId(clients.get(1).getClientId())
              .orderStatus(OrderStatus.DELIVERED)
              .productsInBasketIds(Arrays.asList("P014", "P015", "P016"))
              .orderSubmittedDate(LocalDateTime.of(2024, 8, 6, 11, 0))
              .paymentType(PaymentType.CREDIT)
              .totalAmount(new BigDecimal("40970.00"))
              .build());

      orders.add(
          Order.builder()
              .orderId(this.generateBookingUuid())
              .clientId(clients.get(3).getClientId())
              .orderStatus(OrderStatus.PENDING)
              .productsInBasketIds(Arrays.asList("P017", "P018"))
              .orderSubmittedDate(LocalDateTime.of(2024, 8, 7, 14, 30))
              .paymentType(PaymentType.CASH)
              .totalAmount(new BigDecimal("44980.00"))
              .build());

      orders.add(
          Order.builder()
              .orderId(this.generateBookingUuid())
              .clientId(clients.get(5).getClientId())
              .orderStatus(OrderStatus.CONFIRMED)
              .productsInBasketIds(Arrays.asList("P001", "P012", "P014"))
              .orderSubmittedDate(LocalDateTime.of(2024, 8, 8, 15, 0))
              .paymentType(PaymentType.UPFRONT)
              .totalAmount(new BigDecimal("31970.00"))
              .build());

      log.info("Bookings data created and loaded OK!!");
    } catch (Exception e) {
      log.error("Could not load bookings data at loadData method");
    }
  }

  public List<Order> getOrders(Integer limit) {
    return this.orders.subList(0, limit);
  }

  public List<Order> getAllOrders() {
    return this.orders;
  }
}
