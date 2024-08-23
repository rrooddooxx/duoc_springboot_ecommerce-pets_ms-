package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories;

import com.matrix.duoc_springboot_ecommerce_pets_ms.domain.Product;
import com.matrix.duoc_springboot_ecommerce_pets_ms.domain.ProductType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class ProductRepository {
  private final ArrayList<Product> products = new ArrayList<>();

  public ProductRepository() {
    this.loadData();
  }

  private void loadData() {
    try {
      products.add(
          Product.builder()
              .productId("P001")
              .productType(ProductType.COMIDA)
              .productBrand("Royal Canin")
              .productName("Medium Adult")
              .weightInGrams(1500.0)
              .currentStock(50)
              .price(new BigDecimal("12990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P002")
              .productType(ProductType.COMIDA)
              .productBrand("Pro Plan")
              .productName("Sensitive Skin")
              .weightInGrams(3000.0)
              .currentStock(0) // Out of stock
              .price(new BigDecimal("24990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P003")
              .productType(ProductType.HIGIENE)
              .productBrand("Pet Clean")
              .productName("Shampoo Perros")
              .weightInGrams(500.0)
              .currentStock(30)
              .price(new BigDecimal("3990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P004")
              .productType(ProductType.HIGIENE)
              .productBrand("Furminator")
              .productName("Cepillo Anti Caída")
              .weightInGrams(200.0)
              .currentStock(100)
              .price(new BigDecimal("2990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P005")
              .productType(ProductType.CUIDADO)
              .productBrand("Bayer")
              .productName("Advantix Pipeta")
              .weightInGrams(10.0)
              .currentStock(20)
              .price(new BigDecimal("15990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P006")
              .productType(ProductType.CUIDADO)
              .productBrand("Virbac")
              .productName("Effipro Spray")
              .weightInGrams(250.0)
              .currentStock(0) // Out of stock
              .price(new BigDecimal("25990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P007")
              .productType(ProductType.MEDICAMENTOS)
              .productBrand("Zoetis")
              .productName("Apoquel")
              .weightInGrams(5.0)
              .currentStock(15)
              .price(new BigDecimal("17990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P008")
              .productType(ProductType.MEDICAMENTOS)
              .productBrand("Elanco")
              .productName("Comfortis")
              .weightInGrams(2.5)
              .currentStock(5)
              .price(new BigDecimal("9990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P009")
              .productType(ProductType.PRENDAS)
              .productBrand("DogChic")
              .productName("Chaleco de Lana")
              .weightInGrams(150.0)
              .currentStock(40)
              .price(new BigDecimal("12990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P010")
              .productType(ProductType.PRENDAS)
              .productBrand("PetFashion")
              .productName("Impermeable")
              .weightInGrams(300.0)
              .currentStock(25)
              .price(new BigDecimal("14990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P011")
              .productType(ProductType.COMIDA)
              .productBrand("Hill's")
              .productName("Science Diet")
              .weightInGrams(2000.0)
              .currentStock(75)
              .price(new BigDecimal("11990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P012")
              .productType(ProductType.COMIDA)
              .productBrand("NutraGold")
              .productName("Indoor Cat")
              .weightInGrams(1500.0)
              .currentStock(30)
              .price(new BigDecimal("15990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P013")
              .productType(ProductType.HIGIENE)
              .productBrand("BioClean")
              .productName("Toallitas Húmedas")
              .weightInGrams(250.0)
              .currentStock(45)
              .price(new BigDecimal("4590.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P014")
              .productType(ProductType.HIGIENE)
              .productBrand("PetGuard")
              .productName("Pasta Dental")
              .weightInGrams(100.0)
              .currentStock(60)
              .price(new BigDecimal("2990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P015")
              .productType(ProductType.CUIDADO)
              .productBrand("Advocate")
              .productName("Antipulgas")
              .weightInGrams(50.0)
              .currentStock(10)
              .price(new BigDecimal("19990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P016")
              .productType(ProductType.CUIDADO)
              .productBrand("Frontline")
              .productName("Spray Antiparasitario")
              .weightInGrams(250.0)
              .currentStock(8)
              .price(new BigDecimal("17990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P017")
              .productType(ProductType.MEDICAMENTOS)
              .productBrand("MSD")
              .productName("Bravecto")
              .weightInGrams(10.0)
              .currentStock(7)
              .price(new BigDecimal("20990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P018")
              .productType(ProductType.MEDICAMENTOS)
              .productBrand("Pfizer")
              .productName("Rimadyl")
              .weightInGrams(5.0)
              .currentStock(3)
              .price(new BigDecimal("23990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P019")
              .productType(ProductType.PRENDAS)
              .productBrand("PetStyle")
              .productName("Sweater para Perro")
              .weightInGrams(180.0)
              .currentStock(50)
              .price(new BigDecimal("11990.00"))
              .build());

      products.add(
          Product.builder()
              .productId("P020")
              .productType(ProductType.PRENDAS)
              .productBrand("UrbanPets")
              .productName("Chaqueta de Invierno")
              .weightInGrams(350.0)
              .currentStock(0) // Out of stock
              .price(new BigDecimal("13990.00"))
              .build());

    } catch (Exception e) {
      log.error("Error loading product data in repository");
    }
  }

  public List<Product> getProducts(int limit) {
    return this.products.subList(0, limit);
  }

  public List<Product> getAllProducts() {
    return this.products;
  }
}
