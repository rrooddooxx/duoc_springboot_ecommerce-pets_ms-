package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matrix.duoc_springboot_ecommerce_pets_ms.application.services.OrderService;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.dto.NewOrderDTO;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.mapper.OrderControllerMapper;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.Order;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.OrderStatus;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(OrderController.class)
@Import(OrderControllerMapper.class)
public class OrderControllerTests {

  @Autowired ObjectMapper objectMapper;
  @Autowired MockMvc mockMvc;
  @Autowired OrderControllerMapper resMapper;
  @MockBean private OrderService orderService;

  @Test
  @DisplayName("[GET] FOUND - ORDER BY ID")
  void getOrderByIdSuccess() throws Exception {

    Order foundOrder = new Order();
    foundOrder.setOrderId("123");
    foundOrder.setOrderStatus(OrderStatus.PENDING);

    when(orderService.getOrderById("123")).thenReturn(Optional.of(foundOrder));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/orders/123"))
        .andExpect(MockMvcResultMatchers.status().is(200))
        .andExpect(MockMvcResultMatchers.jsonPath("$.orderId", is("123")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.orderStatus", is("PENDING")));

    when(orderService.getOrderById("999")).thenReturn(Optional.empty());

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/orders/999"))
        .andExpect(MockMvcResultMatchers.status().is(404));
  }

  @Test
  @DisplayName("[GET] NOT FOUND - ORDER BY ID")
  void getOrderByIdNotFound() throws Exception {

    Order foundOrder = new Order();
    foundOrder.setOrderId("123");
    foundOrder.setOrderStatus(OrderStatus.PENDING);

    when(orderService.getOrderById("999")).thenReturn(Optional.empty());

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/orders/999"))
        .andExpect(MockMvcResultMatchers.status().is(404));
  }

  @Test
  @DisplayName("[POST] CREATE NEW ORDER")
  void createNewOrder() throws Exception {

    NewOrderDTO newOrderDTO = new NewOrderDTO();
    String newOrderId = "123";

    String request = objectMapper.writeValueAsString(newOrderDTO);

    when(orderService.createNewOrder(Mockito.any(NewOrderDTO.class))).thenReturn(newOrderId);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
        .andExpect(MockMvcResultMatchers.status().is(200))
        .andExpect(MockMvcResultMatchers.jsonPath("$.new_order_id", is("123")));
  }

}
