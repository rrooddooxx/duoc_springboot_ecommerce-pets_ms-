package com.matrix.duoc_springboot_ecommerce_pets_ms.application.services;

import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.ClientRepository;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.Client;
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
    return this.clientRepository.findAll();
  }

  public List<Client> getAllClients() {
    return this.clientRepository.findAll();
  }

  public Optional<Client> getClientById(Long clientId) {
    return this.clientRepository.findById(clientId);
  }
}
