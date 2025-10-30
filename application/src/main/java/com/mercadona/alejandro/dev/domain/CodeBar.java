package com.mercadona.alejandro.dev.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeBar {
  private String codeCountry;
  private String codeStore;
  private String codeProduct;
}
