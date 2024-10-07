package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers;

import com.matrix.duoc_springboot_ecommerce_pets_ms.application.services.ProductService;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.mapper.ProductControllerMapper;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.Product;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ProductController.class)
@Import({ProductControllerMapper.class})
public class ProductControllerTests {

  @Autowired MockMvc mockMvc;
  @MockBean ProductService productService;

  @Test
  @DisplayName("[GET] SUCCESS - GET ALL PRODUCTS")
  void getAllProductsSuccess() throws Exception {

    List<Product> mockProductsResponse =
        List.of(
            Product.builder().productId("123").build(), Product.builder().productId("456").build());

    Mockito.when(productService.getAllProducts()).thenReturn(mockProductsResponse);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/v1/products").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$['_embedded']['productList'][0].productId")
                .value("123"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$['_embedded']['productList'][1].productId")
                .value("456"));
  }

  @Test
  @DisplayName("[GET] SUCCESS - GET PRODUCT BY ID")
  void getProductByIdSuccess() throws Exception {
    String productId = "987";
    Optional<Product> mockResponse = Optional.of(Product.builder().productId(productId).build());

    Mockito.when(productService.getProductById(productId)).thenReturn(mockResponse);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/v1/products/987")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
        .andExpect(MockMvcResultMatchers.jsonPath("$['productId']").value("987"));
  }
}
