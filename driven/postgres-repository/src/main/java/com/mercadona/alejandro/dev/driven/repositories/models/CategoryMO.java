package com.mercadona.alejandro.dev.driven.repositories.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class CategoryMO {

  @Id
  @SequenceGenerator(name = "category_sequence", sequenceName = "category_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

}
