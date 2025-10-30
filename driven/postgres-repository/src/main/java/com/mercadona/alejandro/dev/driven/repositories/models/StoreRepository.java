package com.mercadona.alejandro.dev.driven.repositories.models;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<StoreMO, Long>, JpaSpecificationExecutor<StoreMO> {

  @EntityGraph(attributePaths = {"country", "assortments"},
    type = EntityGraph.EntityGraphType.FETCH)
  Page<StoreMO> findAll(Pageable pageable);

  @Query("""
    SELECT c
    FROM StoreMO c
    INNER JOIN c.country
    WHERE c.code = :code
""")
  Optional<StoreMO> findByCode(String code);



  // Page<StoreProjection> findAll(Pageable pageable);
}
