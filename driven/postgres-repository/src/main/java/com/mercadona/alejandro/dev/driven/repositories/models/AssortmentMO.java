package com.mercadona.alejandro.dev.driven.repositories.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assortment")
public class AssortmentMO {

  @EmbeddedId
  private AssortmentPk id;

  @MapsId(value = "storeId")
  @ManyToOne
  private StoreMO store;

  @MapsId(value = "productId")
  @ManyToOne
  private ProductMO product;

}
