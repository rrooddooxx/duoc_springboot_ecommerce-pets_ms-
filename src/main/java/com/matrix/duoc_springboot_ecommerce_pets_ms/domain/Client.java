package com.matrix.duoc_springboot_ecommerce_pets_ms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
  private String clientId;
  private String clientFirstName;
  private String clientLastName;
  private String clientEmail;
  private String clientPhone;
}
