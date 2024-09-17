package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers;

import com.matrix.duoc_springboot_ecommerce_pets_ms.application.services.OrderService;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.dto.NewOrderDTO;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.dto.UpdateOrderStatusDTO;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.Order;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.OrderStatus;
import jakarta.validation.Valid;
import java.util.HashMap;
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

  @PostMapping()
  public ResponseEntity<HashMap<String, String>> createNewOrder(
      @Valid @RequestBody NewOrderDTO newOrderDTO) {
    String newOrderId = this.orderService.createNewOrder(newOrderDTO);
    HashMap<String, String> response = new HashMap<>();
    response.put("new_order_id", newOrderId);

    return ResponseEntity.ok(response);
  }

  @PatchMapping("/{orderId}/{productId}")
  public ResponseEntity<Object> addProductToOrder(
      @PathVariable("orderId") String orderId, @PathVariable("productId") String productId) {
    this.orderService.addProductToOrder(orderId, productId);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{orderId}")
  public ResponseEntity<Object> updateOrderStatus(
      @PathVariable("orderId") String orderId, @RequestBody UpdateOrderStatusDTO newStatus) {
    this.orderService.updateOrderStatus(orderId, newStatus.getStatus());
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{orderId}")
  public ResponseEntity<Object> deleteOrderById(@PathVariable("orderId") String orderId) {
    this.orderService.deleteOrder(orderId);
    return ResponseEntity.ok().build();
  }
}
