package com.mercadona.alejandro.dev.driven.repositories;

import com.mercadona.alejandro.dev.driven.repositories.models.StoreMO;
import com.mercadona.alejandro.dev.driven.repositories.projections.StoreProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface StoreCustomRepository {

  Page<Long> findIdsAll(Specification<StoreMO> specification, Pageable pageable);

  Page<StoreProjection> findAllProjections(Specification<StoreMO> specification, Pageable pageable);

}
