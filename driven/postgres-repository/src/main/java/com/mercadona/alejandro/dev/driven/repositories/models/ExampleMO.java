package com.mercadona.alejandro.dev.driven.repositories.models;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "o_examples")
public class ExampleMO {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "creation_time")
  private OffsetDateTime creationTime;

  @Column(name = "identification_type")
  private IdentificationTypesMOEnum identificationType;

  @Column(name = "identification")
  private String identification;

  @Column(name = "days_in_week")
  private Integer numberOfDaysInWeek;

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    final var thisEffectiveClass = this instanceof HibernateProxy thisProxy
      ? thisProxy.getHibernateLazyInitializer().getPersistentClass()
      : getClass();
    final var oEffectiveClass = o instanceof HibernateProxy oProxy
      ? oProxy.getHibernateLazyInitializer().getPersistentClass()
      : o.getClass();
    if (getClass() != o.getClass() && thisEffectiveClass != oEffectiveClass) return false;
    final var entity = (ExampleMO) o;
    return getId() != null && Objects.equals(getId(), entity.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy proxy
      ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
      : getClass().hashCode();
  }
}
