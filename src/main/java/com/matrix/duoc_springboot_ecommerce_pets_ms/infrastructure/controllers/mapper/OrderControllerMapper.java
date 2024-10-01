package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.OrderController;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.Order;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.OrderStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component
public class OrderControllerMapper {
  public EntityModel<Map<String, String>> mapNewOrderToEntityModel(String orderId) {
    Map<String, String> response =
        new HashMap<>() {
          {
            put("new_order_id", orderId);
          }
        };
    return EntityModel.of(
        response,
        linkTo(methodOn(OrderController.class).getOrderById(orderId)).withRel("new_order_detail"));
  }

  public EntityModel<Order> mapDomainToEntityModel(Order order) {
    return EntityModel.of(
        order,
        linkTo(methodOn(OrderController.class).getOrderById(order.getOrderId())).withSelfRel());
  }

  public EntityModel<OrderStatus> mapOrderStatusToEntityModel(
      OrderStatus orderStatus, String orderId) {
    return EntityModel.of(
        orderStatus,
        linkTo(methodOn(OrderController.class).getOrderStatusById(orderId)).withSelfRel(),
        linkTo(methodOn(OrderController.class).getOrderById(orderId)).withRel("order_detail"));
  }

  public List<EntityModel<Order>> mapListToEntities(List<Order> orders) {
    return orders.stream().map(this::mapDomainToEntityModel).toList();
  }

  public CollectionModel<EntityModel<Order>> mapListToCollection(List<EntityModel<Order>> orders) {
    return CollectionModel.of(
        orders, linkTo(methodOn(OrderController.class).getAllOrders()).withRel("all_orders"));
  }
}
