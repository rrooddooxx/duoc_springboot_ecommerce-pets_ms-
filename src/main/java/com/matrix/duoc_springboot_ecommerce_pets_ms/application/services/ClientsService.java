package com.matrix.duoc_springboot_ecommerce_pets_ms.application.services;

import com.matrix.duoc_springboot_ecommerce_pets_ms.domain.Client;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.ClientRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientsService {
  private final ClientRepository clientRepository;

  public List<Client> getClients(Integer limit) {
    return this.clientRepository.getClients(limit);
  }

  public List<Client> getAllClients() {
    return this.clientRepository.getAllClients();
  }

  public Optional<Client> getClientById(String clientId) {
    return this.clientRepository.getAllClients().stream()
        .filter(client -> client.getClientId().equals(clientId))
        .findFirst();
  }
}
