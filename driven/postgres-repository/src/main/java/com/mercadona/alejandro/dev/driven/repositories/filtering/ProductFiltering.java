package com.mercadona.alejandro.dev.driven.repositories.filtering;

import com.mercadona.alejandro.dev.domain.ProductFilter;
import com.mercadona.alejandro.dev.driven.repositories.models.ProductMO;
import com.mercadona.alejandro.dev.driven.repositories.models.ProductMO_;
import com.mercadona.alejandro.dev.driven.repositories.models.TagMO;
import com.mercadona.alejandro.dev.driven.repositories.models.TagMO_;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.Set;

@UtilityClass
public class ProductFiltering {

  public static Specification<ProductMO> process(ProductFilter productFilter) {
    Specification<ProductMO> spec = empty();

    if(productFilter.hasTags()) spec = spec.and(hasTags(productFilter.getTags()));
    if(productFilter.hasName()) spec = spec.and(hasName(productFilter.getName()));

    return spec;
  }

  private static Specification<ProductMO> hasName(String name) {
    return ((root, query, cb) -> {
      return cb.equal(root.get(ProductMO_.name), name);
    });
  }


  public static Specification<ProductMO> hasTags(Set<String> tags){
    return ((root, query, cb) -> {
      root.fetch(ProductMO_.TAGS, JoinType.INNER);

      Join<ProductMO, TagMO> tagsJoin = root.join(ProductMO_.TAGS, JoinType.INNER);
      return tagsJoin.get("name").in(tags);
    });
  }

  private static Specification<ProductMO> empty() {
    return ((root, query, cb) -> {
      return cb.and();
    });
  }

}
