package com.mercadona.alejandro.dev.driven.repositories;

import com.mercadona.alejandro.dev.driven.repositories.models.ProductDTO;
import com.mercadona.alejandro.dev.driven.repositories.models.ProductMO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

import static org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH;

@Repository
public interface ProductRepository extends JpaRepository<ProductMO, Long>, JpaSpecificationExecutor<ProductMO> {

  @Query("""
          SELECT p FROM ProductMO p
          WHERE p.code = :code
    """)
  Optional<ProductMO> findByCode(String code);

  @Query("""
        SELECT p FROM ProductMO p
        LEFT JOIN FETCH p.assortments a
        LEFT JOIN FETCH a.store s
        LEFT JOIN FETCH p.parent pr
        WHERE p.code = :codProduct
        AND s.code = :codeStore
    """)
  ProductMO findProductByCodeBar(String codProduct, String codeStore);

  @Query(value = """
    SELECT p FROM ProductMO p
    INNER JOIN FETCH p.category c
    LEFT JOIN FETCH p.parent pr
    LEFT JOIN FETCH p.assortments
    WHERE c.id =:categoryId
    """, countQuery = """
    SELECT COUNT(p) FROM ProductMO p
    INNER JOIN p.category c
    WHERE c.id =:categoryId
    """)
  Page<ProductMO> findAllByCategory(Long categoryId, Pageable pageable);

  @Query(value = """
    SELECT p.id, count(*) OVER() AS total_count
    FROM Product p
    INNER JOIN category c on (p.category_id = c.id)
    WHERE c.id =:categoryId
    """, nativeQuery = true)
  List<Tuple> findAllbyCategoryWithOutQueryCount(Long categoryId, PageRequest pageRequest);


  @Query(value = """
        SELECT p.id AS id, p.name AS name, p.quantity AS quantity, p.state AS state
        FROM ProductMO p
        INNER JOIN p.category c
        WHERE c.id =:categoryId
    """
  )
  Page<ProductDTO> findAllDtoByCategory(Long categoryId, Pageable pageable);

  @Query(value = """
    SELECT p
        FROM ProductMO p
        JOIN FETCH p.category
        JOIN FETCH p.assortments a
        JOIN FETCH a.store s
        JOIN FETCH s.country
        LEFT JOIN FETCH p.parent pr
        LEFT JOIN FETCH pr.category
        LEFT JOIN FETCH p.children ch
    WHERE
        s.code = :storeId
    """)
  List<ProductMO> findAllProductsByProductAndStore(String storeId);

  @Query(value = """
    SELECT DISTINCT p
        FROM ProductMO p
        JOIN FETCH p.category
        JOIN FETCH p.assortments a
        JOIN FETCH a.store s
        JOIN FETCH s.country
        LEFT JOIN FETCH p.parent pr
        LEFT JOIN FETCH pr.category
        LEFT JOIN FETCH p.children ch
    WHERE
        s.code = :storeId
    """)
  @QueryHints(value = @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false"))
  List<ProductMO> findAllProductsByProductAndStoreV2(String storeId);

}
