package com.mercadona.alejandro.dev.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {
  public Long id;
  public String name;
  private String code;
  private Country country;
  private Long idCountry;
  private String countryCode;
}
