package com.matrix.duoc_springboot_ecommerce_pets_ms.application.services;

import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;

  /*public List<Order> getBookings(Integer limit) {
    return this.orderRepository.getBookings(limit);
  }

  public List<Order> getAllBookings() {
    return this.orderRepository.getAllBookings();
  }

  public Optional<Order> getBookingById(String bookingId) {
    return this.orderRepository.getAllBookings().stream()
        .filter(order -> order.getBookingId().equals(bookingId))
        .findFirst();
  }*/
}
