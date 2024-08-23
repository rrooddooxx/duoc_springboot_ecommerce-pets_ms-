package com.matrix.duoc_springboot_ecommerce_pets_ms.application.services;

import com.matrix.duoc_springboot_ecommerce_pets_ms.domain.Order;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.OrderRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;

  public List<Order> getOrders(Integer limit) {
    return this.orderRepository.getOrders(limit);
  }

  public List<Order> getAllOrders() {
    return this.orderRepository.getAllOrders();
  }

  public Optional<Order> getOrderById(String orderId) {
    return this.orderRepository.getAllOrders().stream()
        .filter(order -> order.getOrderId().equals(orderId))
        .findFirst();
  }
}
