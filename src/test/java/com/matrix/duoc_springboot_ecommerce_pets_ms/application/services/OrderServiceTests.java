package com.matrix.duoc_springboot_ecommerce_pets_ms.application.services;

import com.matrix.duoc_springboot_ecommerce_pets_ms.application.services.exception.ResourceNotFoundException;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.dto.NewOrderDTO;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.ClientRepository;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.OrderRepository;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.Client;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.Order;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.OrderStatus;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.PaymentType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

  @Mock private OrderRepository orderRepository;
  @Mock private ClientRepository clientRepository;

  @InjectMocks private OrderService orderService;

  @Test
  @DisplayName("[SUCCESS] CREATE NEW ORDER")
  void createNewOrder() {
    Client client =
        Client.builder()
            .clientId(1L)
            .clientFirstName("Juan")
            .clientLastName("Pérez")
            .clientEmail("juan@perez.com")
            .build();

    NewOrderDTO newOrderDTO =
        NewOrderDTO.builder()
            .clientId(1L)
            .orderStatus(OrderStatus.PENDING)
            .orderSubmittedDate(LocalDateTime.now())
            .paymentType(PaymentType.CREDIT)
            .build();

    Order savedOrder =
        Order.builder()
            .orderId("123")
            .client(client)
            .orderStatus(OrderStatus.PENDING)
            .orderSubmittedDate(newOrderDTO.getOrderSubmittedDate())
            .paymentType(newOrderDTO.getPaymentType())
            .totalAmount(new BigDecimal("0"))
            .build();

    Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
    Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(savedOrder);

    String newOrderId = orderService.createNewOrder(newOrderDTO);

    Assertions.assertEquals("123", newOrderId);
    Mockito.verify(orderRepository).save(Mockito.any(Order.class));
  }

  @Test
  @DisplayName("[FAILURE] CREATE NEW ORDER - CLIENT NOT FOUND")
  void createNewOrderClientNotFound() {
    NewOrderDTO newOrderDTO =
        NewOrderDTO.builder()
            .clientId(999L)
            .orderStatus(OrderStatus.PENDING)
            .orderSubmittedDate(LocalDateTime.now())
            .paymentType(PaymentType.CREDIT)
            .build();

    Mockito.when(clientRepository.findById(999L)).thenReturn(Optional.empty());

    ResourceNotFoundException exception =
        Assertions.assertThrows(
            ResourceNotFoundException.class, () -> orderService.createNewOrder(newOrderDTO));

    Assertions.assertEquals(
        "Cliente con id (999) no encontrado en el sistema", exception.getMessage());
  }

  @Test
  @DisplayName("[SUCCESS] ADD PRODUCT TO ORDER")
  void addProductToOrder() {
    Order order =
        Order.builder().orderId("123").productIdsInOrder(new ArrayList<>(List.of("prod1"))).build();

    Mockito.when(orderRepository.findById("123")).thenReturn(Optional.of(order));

    orderService.addProductToOrder("123", "prod2");

    Mockito.verify(orderRepository).save(order);
    Assertions.assertTrue(order.getProductIdsInOrder().contains("prod2"));
  }

  @Test
  @DisplayName("[FAILURE] ADD PRODUCT TO ORDER - ORDER NOT FOUND")
  void addProductToOrderOrderNotFound() {
    Mockito.when(orderRepository.findById("999")).thenReturn(Optional.empty());

    ResourceNotFoundException exception =
        Assertions.assertThrows(
            ResourceNotFoundException.class, () -> orderService.addProductToOrder("999", "prod2"));

    Assertions.assertEquals(
        "Orden con id (999) no encontrada en el sistema", exception.getMessage());
  }

  @Test
  @DisplayName("[SUCCESS] UPDATE ORDER STATUS")
  void updateOrderStatus() {
    Order order = Order.builder().orderId("123").orderStatus(OrderStatus.PENDING).build();

    Mockito.when(orderRepository.findById("123")).thenReturn(Optional.of(order));

    orderService.updateOrderStatus("123", OrderStatus.SHIPPED);

    Mockito.verify(orderRepository).save(order);
    Assertions.assertEquals(OrderStatus.SHIPPED, order.getOrderStatus());
  }

  @Test
  @DisplayName("[FAILURE] UPDATE ORDER STATUS - ORDER NOT FOUND")
  void updateOrderStatusOrderNotFound() {
    Mockito.when(orderRepository.findById("999")).thenReturn(Optional.empty());

    ResourceNotFoundException exception =
        Assertions.assertThrows(
            ResourceNotFoundException.class,
            () -> orderService.updateOrderStatus("999", OrderStatus.SHIPPED));

    Assertions.assertEquals(
        "Orden con id (999) no encontrada en el sistema", exception.getMessage());
  }

  @Test
  @DisplayName("[SUCCESS] DELETE ORDER")
  void deleteOrder() {
    Order order = Order.builder().orderId("123").build();

    Mockito.when(orderRepository.findById("123")).thenReturn(Optional.of(order));

    orderService.deleteOrder("123");

    Mockito.verify(orderRepository).deleteById("123");
  }

  @Test
  @DisplayName("[FAILURE] DELETE ORDER - ORDER NOT FOUND")
  void deleteOrderNotFound() {
    Mockito.when(orderRepository.findById("999")).thenReturn(Optional.empty());

    ResourceNotFoundException exception =
        Assertions.assertThrows(
            ResourceNotFoundException.class, () -> orderService.deleteOrder("999"));

    Assertions.assertEquals(
        "Orden con id (999) no encontrada en el sistema", exception.getMessage());
  }
}
