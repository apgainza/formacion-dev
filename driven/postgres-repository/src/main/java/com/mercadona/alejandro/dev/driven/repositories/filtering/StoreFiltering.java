package com.mercadona.alejandro.dev.driven.repositories.filtering;

import com.mercadona.alejandro.dev.domain.StoreFilter;
import com.mercadona.alejandro.dev.driven.repositories.models.StoreMO;
import com.mercadona.alejandro.dev.driven.repositories.models.StoreMO_;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class StoreFiltering {

  public static Specification<StoreMO> process(StoreFilter storeFilter) {
    Specification<StoreMO> spec = empty();

    if(StringUtils.isNotBlank(storeFilter.code())){
      spec = spec.and(getCodeFilter(storeFilter.code()));
    }

    if(StringUtils.isNotBlank(storeFilter.name())){
      spec = spec.and(getNameFilter(storeFilter.name()));
    }

    return spec;
  }

  private static Specification<StoreMO> getNameFilter(String name) {
    return ((root, query, cb) ->
      cb.like(cb.lower(root.get(StoreMO_.NAME)), "%" + name.toLowerCase() + "%"));
  }

  private static Specification<StoreMO> getCodeFilter(String code) {
    return ((root, query, cb) ->
      cb.equal(root.get(StoreMO_.CODE), code));
  }

  private static Specification<StoreMO> empty() {
    return ((root, query, cb) -> cb.and());
  }

}
