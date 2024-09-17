package com.matrix.duoc_springboot_ecommerce_pets_ms.application.services;

import com.matrix.duoc_springboot_ecommerce_pets_ms.application.services.exception.ResourceNotFoundException;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.dto.NewOrderDTO;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.ClientRepository;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.OrderRepository;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.Client;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.Order;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.OrderStatus;
import java.math.BigDecimal;
import java.util.ArrayList;
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
  private final ClientRepository clientRepository;

  public List<Order> getOrders(Integer limit) {
    return this.orderRepository.findAll();
  }

  public List<Order> getAllOrders() {
    return this.orderRepository.findAll();
  }

  public Optional<Order> getOrderById(String orderId) {
    return this.orderRepository.findById(orderId);
  }

  public String createNewOrder(NewOrderDTO newOrderDTO) {
    Optional<Client> foundClient = this.clientRepository.findById(newOrderDTO.getClientId());

    if (foundClient.isEmpty()) {
      throw new ResourceNotFoundException(
          String.format(
              "Cliente con id (%s) no encontrado en el sistema", newOrderDTO.getClientId()));
    }

    Order newOrder =
        Order.builder()
            .orderStatus(newOrderDTO.getOrderStatus())
            .client(foundClient.get())
            .orderSubmittedDate(newOrderDTO.getOrderSubmittedDate())
            .paymentType(newOrderDTO.getPaymentType())
            .totalAmount(new BigDecimal("0"))
            .build();

    Order createdOrder = this.orderRepository.save(newOrder);
    return createdOrder.getOrderId();
  }

  public void addProductToOrder(String orderId, String productId) {
    Optional<Order> foundOrder = this.orderRepository.findById(orderId);

    if (foundOrder.isEmpty()) {
      throw new ResourceNotFoundException(
          String.format("Orden con id (%s) no encontrada en el sistema", orderId));
    }

    Order updatedOrder = foundOrder.get();
    List<String> productList = new ArrayList<>(updatedOrder.getProductIdsInOrder());
    productList.add(productId);
    updatedOrder.setProductIdsInOrder(productList);
    this.orderRepository.save(updatedOrder);
  }

  public void updateOrderStatus(String orderId, OrderStatus newStatus) {
    Optional<Order> foundOrder = this.orderRepository.findById(orderId);

    if (foundOrder.isEmpty()) {
      throw new ResourceNotFoundException(
              String.format("Orden con id (%s) no encontrada en el sistema", orderId));
    }

    Order updatedOrder = foundOrder.get();
    updatedOrder.setOrderStatus(newStatus);
    this.orderRepository.save(updatedOrder);
  }

  public void deleteOrder(String orderId) {
    Optional<Order> foundOrder = this.orderRepository.findById(orderId);

    if (foundOrder.isEmpty()) {
      throw new ResourceNotFoundException(
          String.format("Orden con id (%s) no encontrada en el sistema", orderId));
    }

    this.orderRepository.deleteById(orderId);
  }
}
