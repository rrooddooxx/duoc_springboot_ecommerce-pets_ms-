package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories;

import com.matrix.duoc_springboot_ecommerce_pets_ms.domain.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderRepository {
  private final ArrayList<Order> bookingsList = new ArrayList<>();
  private final ClientRepository clientRepository;
  private final ProductRepository productRepository;

  public OrderRepository(ClientRepository clientRepository, ProductRepository productRepository)
      throws Exception {
    this.clientRepository = clientRepository;
    this.productRepository = productRepository;
    this.loadData();
  }

  private String generateBookingUuid() {
    return UUID.randomUUID().toString();
  }

  private void loadData() throws Exception {
    try {
/*
      List<Client> clients = this.clientRepository.getAllClients();
      List<Product> products =
          this.productRepository.getAllRooms().stream()
              .filter(product -> product.getStockStatus().equals(StockStatus.AVAILABLE))
              .toList();

      this.bookingsList.add(
          Order.builder()
              .bookingId(this.generateBookingUuid())
              .bookingRequestDate(LocalDateTime.now())
              .bookingStartDate(LocalDateTime.of(2024, 8, 22, 21, 0))
              .bookingEndDate(LocalDateTime.of(2024, 8, 24, 21, 0))
              .fee(new BigDecimal("2700"))
              .clientId(clients.getFirst())
              .roomId(products.getFirst().getRoomId())
              .build());
      this.bookingsList.add(
          Order.builder()
              .bookingId(this.generateBookingUuid())
              .bookingRequestDate(LocalDateTime.now())
              .bookingStartDate(LocalDateTime.of(2024, 8, 22, 21, 0))
              .bookingEndDate(LocalDateTime.of(2024, 8, 24, 21, 0))
              .fee(new BigDecimal("2700"))
              .clientId(clients.get(1))
              .roomId(products.get(1).getRoomId())
              .build());
      this.bookingsList.add(
          Order.builder()
              .bookingId(this.generateBookingUuid())
              .bookingRequestDate(LocalDateTime.now())
              .bookingStartDate(LocalDateTime.of(2024, 8, 22, 21, 0))
              .bookingEndDate(LocalDateTime.of(2024, 8, 24, 21, 0))
              .fee(new BigDecimal("2700"))
              .clientId(clients.get(2))
              .roomId(products.get(2).getRoomId())
              .build());
      this.bookingsList.add(
          Order.builder()
              .bookingId(this.generateBookingUuid())
              .bookingRequestDate(LocalDateTime.now())
              .bookingStartDate(LocalDateTime.of(2024, 8, 22, 21, 0))
              .bookingEndDate(LocalDateTime.of(2024, 8, 24, 21, 0))
              .fee(new BigDecimal("2700"))
              .clientId(clients.get(3))
              .roomId(products.get(3).getRoomId())
              .build());
      this.bookingsList.add(
          Order.builder()
              .bookingId(this.generateBookingUuid())
              .bookingRequestDate(LocalDateTime.now())
              .bookingStartDate(LocalDateTime.of(2024, 8, 22, 21, 0))
              .bookingEndDate(LocalDateTime.of(2024, 8, 24, 21, 0))
              .fee(new BigDecimal("2700"))
              .clientId(clients.get(4))
              .roomId(products.get(4).getRoomId())
              .build());*/
      log.info("Bookings data created and loaded OK!!");
    } catch (Exception e) {
      log.error("Could not load bookings data at loadData method");
    }
  }

  public List<Order> getBookings(Integer limit) {
    return this.bookingsList.subList(0, limit);
  }

  public List<Order> getAllBookings() {
    System.out.println(this.bookingsList);
    return this.bookingsList;
  }
}
