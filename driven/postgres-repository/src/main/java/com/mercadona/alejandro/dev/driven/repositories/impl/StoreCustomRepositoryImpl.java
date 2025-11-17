package com.mercadona.alejandro.dev.driven.repositories.impl;

import com.mercadona.alejandro.dev.driven.repositories.StoreCustomRepository;
import com.mercadona.alejandro.dev.driven.repositories.models.*;
import com.mercadona.alejandro.dev.driven.repositories.projections.StoreProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreCustomRepositoryImpl implements StoreCustomRepository {

  @PersistenceContext
  private final EntityManager em;

  @Override
  public Page<Long> findIdsAll(Specification<StoreMO> specification, Pageable pageable) {

    CriteriaBuilder cb = em.getCriteriaBuilder();

    CriteriaQuery<Long> query = cb.createQuery(Long.class);
    Root<StoreMO> root = query.from(StoreMO.class);
    query.select(root.get("id"));

    if(specification != null){
      query.where(specification.toPredicate(root, query, cb));
    }

    // Aplicar ordenamiento
    if (pageable.getSort().isSorted()) {
      query.orderBy(QueryUtils.toOrders(pageable.getSort(), root, cb));
    }

    // Paginación
    TypedQuery<Long> typedQuery = em.createQuery(query);
    typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
    typedQuery.setMaxResults(pageable.getPageSize());
    List<Long> ids = typedQuery.getResultList();

    // Count
    Long count = count(specification, cb);


    return new PageImpl<>(ids, pageable, count);
  }

  @Override
  public Page<StoreProjection> findAllProjections(Specification<StoreMO> specification, Pageable pageable) {

    CriteriaBuilder cb = em.getCriteriaBuilder();

    // Query para la proyección
    CriteriaQuery<StoreProjection> query = cb.createQuery(StoreProjection.class);
    Root<StoreMO> root = query.from(StoreMO.class);
    Join<StoreMO, CountryMO> country = root.join("country");
    Join<StoreMO, AssortmentMO> assortment = root.join("assortments", JoinType.LEFT);
    Join<AssortmentMO, ProductMO> product = assortment.join("product", JoinType.LEFT);

    // Construir la proyección
    query.select(cb.construct(
      StoreProjection.class,
      root.get("id"),
      root.get("name"),
      root.get("code"),
      country.get("name"),
      country.get("id"),
      country.get("code")));

    // Aplicar filtros del Specification
    if (specification != null) {
      Predicate predicate = specification.toPredicate(root, query, cb);
      if (predicate != null) {
        query.where(predicate);
      }
    }

    // Aplicar ordenamiento
    if (pageable.getSort().isSorted()) {
      query.orderBy(QueryUtils.toOrders(pageable.getSort(), root, cb));
    }

    // Ejecutar con paginación
    TypedQuery<StoreProjection> typedQuery = em.createQuery(query);
    typedQuery.setFirstResult((int) pageable.getOffset());
    typedQuery.setMaxResults(pageable.getPageSize());
    List<StoreProjection> results = typedQuery.getResultList();

    // Contar total
    long total = count(specification, cb);

    return new PageImpl<>(results, pageable, total);
  }

  private long count(Specification<StoreMO> spec, CriteriaBuilder cb) {

    CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
    Root<StoreMO> root = countQuery.from(StoreMO.class);
    countQuery.select(cb.count(root));

    if (spec != null) {
      Predicate predicate = spec.toPredicate(root, countQuery, cb);
      if (predicate != null) {
        countQuery.where(predicate);
      }
    }

    return em.createQuery(countQuery).getSingleResult();
  }
}
