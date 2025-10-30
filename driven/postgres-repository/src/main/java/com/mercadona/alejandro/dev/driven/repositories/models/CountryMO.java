package com.mercadona.alejandro.dev.driven.repositories.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "country")
public class CountryMO {

  @Id
  @SequenceGenerator(name = "country_sequence", sequenceName = "country_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_sequence")
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Column(name = "code", nullable = false, length = 2)
  private String code;
}
