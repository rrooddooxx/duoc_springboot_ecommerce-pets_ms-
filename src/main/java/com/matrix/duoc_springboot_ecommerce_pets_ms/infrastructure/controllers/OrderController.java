package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers;

import com.matrix.duoc_springboot_ecommerce_pets_ms.application.services.OrderService;
import com.matrix.duoc_springboot_ecommerce_pets_ms.domain.Order;
import com.matrix.duoc_springboot_ecommerce_pets_ms.domain.OrderStatus;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

  private final OrderService orderService;

  @GetMapping()
  public ResponseEntity<List<Order>> getAllOrders(
      @RequestParam(required = false) Optional<Integer> limit) {
    List<Order> orders =
        limit.isPresent() ? orderService.getOrders(limit.get()) : orderService.getAllOrders();

    return orders.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(orders);
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<Order> getOrderById(@PathVariable("orderId") String orderId) {
    Optional<Order> foundOrder = orderService.getOrderById(orderId);

    return foundOrder.isEmpty()
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(foundOrder.get());
  }

  @GetMapping("/{orderId}/status")
  public ResponseEntity<OrderStatus> getOrderStatusById(@PathVariable("orderId") String orderId) {
    Optional<Order> foundOrder = orderService.getOrderById(orderId);

    return foundOrder.isEmpty()
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(foundOrder.get().getOrderStatus());
  }
}
