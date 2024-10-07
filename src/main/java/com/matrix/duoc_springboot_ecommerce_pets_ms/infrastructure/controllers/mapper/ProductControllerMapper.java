package com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.controllers.ProductController;
import com.matrix.duoc_springboot_ecommerce_pets_ms.infrastructure.persistence.repositories.entities.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component
public class ProductControllerMapper {
  public EntityModel<Map<String, String>> mapNewProductToEntityModel(String productId) {
    Map<String, String> response =
        new HashMap<>() {
          {
            put("new_product_id", productId);
          }
        };
    return EntityModel.of(
        response,
        linkTo(methodOn(ProductController.class).getProductById(productId))
            .withRel("new_product_detail"));
  }

  public EntityModel<Product> mapDomainToEntityModel(Product product) {
    return EntityModel.of(
        product,
        linkTo(methodOn(ProductController.class).getProductById(product.getProductId()))
            .withSelfRel());
  }

  public EntityModel<Map<String, Integer>> mapProductStockToEntityModel(
      Integer stock, String productId) {
    Map<String, Integer> response =
        new HashMap<>() {
          {
            put("stock", stock);
          }
        };
    return EntityModel.of(
        response,
        linkTo(methodOn(ProductController.class).getProductById(productId)).withSelfRel());
  }

  public List<EntityModel<Product>> mapListToEntities(List<Product> products) {
    return products.stream().map(this::mapDomainToEntityModel).toList();
  }

  public CollectionModel<EntityModel<Product>> mapListToCollection(
      List<EntityModel<Product>> products) {
    return CollectionModel.of(
        products,
        linkTo(methodOn(ProductController.class).getAllProducts()).withRel("all_products"));
  }
}
