package com.mercadona.alejandro.dev.driven.repositories;

import com.mercadona.alejandro.dev.domain.StoreFilter;
import com.mercadona.alejandro.dev.driven.repositories.models.StoreMO;
import com.mercadona.alejandro.dev.driven.repositories.projections.StoreProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<StoreMO, Long>, JpaSpecificationExecutor<StoreMO>, StoreCustomRepository {

  @Query("""
    SELECT c
    FROM StoreMO c
    INNER JOIN c.country
    WHERE c.code = :code
""")
  Optional<StoreMO> findByCode(String code);

  // Método para obtener proyecciones con filtros dinámicos
  @Query("""
    SELECT new com.mercadona.alejandro.dev.driven.repositories.projections.StoreProjection(
        s.id, s.name, s.code, c.name, c.id, c.code)
    FROM StoreMO s
    INNER JOIN s.country c
    WHERE s.id IN :ids
  """)
  List<StoreProjection>findProjectionsByIds(@Param("ids") List<Long> ids, Pageable pageable);

  @EntityGraph(attributePaths = {"country", "assortments"})
  Page<StoreMO> findAll(Specification<StoreMO> spec, Pageable pageable);

}
