package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ECOM_CLIENTS", schema = "PETSECOMMERCE_MS")
public class Client {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long clientId;

  @Column(name = "first_name")
  private String clientFirstName;

  @Column(name = "last_name")
  private String clientLastName;

  @Column(name = "email")
  private String clientEmail;

  @Column(name = "phone")
  private String clientPhone;
}
