package com.mercadona.alejandro.dev.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  private Long id;
  private String code;
  private String name;
  private String quantity;
  private ProductType state;
  private Category category;
  private List<Store> stores;
  private Product parent;
  private List<Product> children;
}
