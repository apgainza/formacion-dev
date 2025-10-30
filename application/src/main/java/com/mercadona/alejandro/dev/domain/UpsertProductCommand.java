package com.mercadona.alejandro.dev.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
public class UpsertProductCommand {

  private String code;
  private String name;
  private Integer quantity;
  private ProductType state = ProductType.ACTIVE;
  private Long parentIdProduct;
  private Long categoryId;
  private Set<String> storeIds;
}
