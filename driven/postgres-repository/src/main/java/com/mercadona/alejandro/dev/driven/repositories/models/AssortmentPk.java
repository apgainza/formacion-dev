package com.mercadona.alejandro.dev.driven.repositories.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AssortmentPk implements Serializable {

  @Column(name = "store_id")
  private Long storeId;

  @Column(name = "product_id")
  private Long productId;
}
