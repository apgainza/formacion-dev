package com.mercadona.alejandro.dev.driven.repositories.models;


public interface ProductDTO {

  public Long getId();
  public String getName();
  public Integer getQuantity();
  public ProductTypeMO getState();
}
