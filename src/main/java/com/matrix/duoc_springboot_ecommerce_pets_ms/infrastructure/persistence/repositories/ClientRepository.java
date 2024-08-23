package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matrix.duoc_springboot_ecommerce_pets_ms.domain.Client;
import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {
  private final ArrayList<Client> clientsList = new ArrayList<>();

  @PostConstruct
  public void init() {
    this.loadData();
  }

  private void loadData() {
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<List<Client>> typeRef = new TypeReference<>() {};
    InputStream inputStream = TypeReference.class.getResourceAsStream("/data/clients.json");
    try {
      List<Client> parsedData = mapper.readValue(inputStream, typeRef);
      parsedData =
          parsedData.stream()
              .map(
                  client ->
                      Client.builder()
                          .clientId(UUID.randomUUID().toString())
                          .clientFirstName(client.getClientFirstName())
                          .clientLastName(client.getClientLastName())
                          .clientPhone(client.getClientPhone())
                          .clientEmail(client.getClientEmail())
                          .build())
              .toList();
      clientsList.addAll(parsedData);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<Client> getClients(int limit) {
    return this.clientsList.subList(0, limit);
  }

  public List<Client> getAllClients() {
    return this.clientsList;
  }
}
