package com.mercadona.alejandro.dev.driven.repositories.models;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductMO {

  @Id
  @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "code", nullable = false, length = 8)
  private String code;

  @Column
  private Integer quantity;

  @Enumerated(EnumType.STRING)
  @Column(name = "state", nullable = false)
  private ProductTypeMO state;

  @ManyToOne
  @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "fk_product_parent"))
  private ProductMO parent;

  @OneToMany(mappedBy = "parent")
  private Set<ProductMO> children;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_category"))
  private CategoryMO category;

  @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
  private List<AssortmentMO> assortments;

  public void addAssortment(AssortmentMO assortment) {
    if(this.assortments == null) {
      this.assortments = new java.util.ArrayList<>();
    }
    this.assortments.add(assortment);
    assortment.setProduct(this);
  }

}
