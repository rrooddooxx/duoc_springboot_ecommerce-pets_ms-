package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers;

import com.matrix.duoc_springboot_ecommerce_pets_ms.application.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

  private final OrderService orderService;

  /*@GetMapping()
  public ResponseEntity<List<Order>> getAllBookings(
      @RequestParam(required = false) Optional<Integer> limit) {
    List<Order> serviceResponse =
        limit.isPresent() ? orderService.getBookings(limit.get()) : orderService.getAllBookings();

    return serviceResponse.isEmpty()
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(serviceResponse);
  }

  @GetMapping("/{bookingId}")
  public ResponseEntity<Order> getBookingById(@PathVariable("bookingId") String bookingId) {
    Optional<Order> foundBooking = orderService.getBookingById(bookingId);

    return foundBooking.isEmpty()
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(foundBooking.get());
  }*/
}
