package com.mercadona.alejandro.dev.driven.repositories;

import com.mercadona.alejandro.dev.driven.repositories.models.ProductMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;

import java.util.List;

import static org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH;

@Repository
public interface ProductMOJpaRepository extends JpaRepository<ProductMO, Long>, JpaSpecificationExecutor<ProductMO> {


  @Query("""
    SELECT DISTINCT f FROM ProductMO f
    LEFT JOIN FETCH f.tags
    WHERE f.id = :productId
    """)
  /*@QueryHints(value = @QueryHint(name = HINT_PASS_DISTINCT_THROUGH,
    value = "false"))*/
  List<ProductMO> findProductsById(Long productId);

  @Query("""
    SELECT DISTINCT f FROM ProductMO f
    LEFT JOIN FETCH f.tags
    WHERE f.id = :productId
    """)
  @QueryHints(value = @QueryHint(name = HINT_PASS_DISTINCT_THROUGH,
    value = "false"))
  List<ProductMO> findProductsByIdWithHints(Long productId);

}
