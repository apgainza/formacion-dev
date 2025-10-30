package com.mercadona.alejandro.dev.driven.repositories.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "store")
public class StoreMO {

  @Id
  @SequenceGenerator(name = "store_sequence", sequenceName = "store_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_sequence")
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Column(name = "code", nullable = false, length = 6)
  private String code;

  @ManyToOne
  @JoinColumn(name = "country_id", nullable = false)
  private CountryMO country;

  @OneToMany(mappedBy = "store")
  private List<AssortmentMO> assortments;
}
