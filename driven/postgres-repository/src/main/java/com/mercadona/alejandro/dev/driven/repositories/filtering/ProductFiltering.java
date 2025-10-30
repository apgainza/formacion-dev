package com.mercadona.alejandro.dev.driven.repositories.filtering;

import com.mercadona.alejandro.dev.domain.ProductFilter;
import com.mercadona.alejandro.dev.driven.repositories.models.ProductMO;
import com.mercadona.alejandro.dev.driven.repositories.models.ProductMO_;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class ProductFiltering {


  public static Specification<ProductMO> process(ProductFilter productFilter) {
    Specification<ProductMO> spec = empty();

    if(productFilter.hasProductId()) spec = spec.and(hasProductId(productFilter.getProductId()));
    // if(productFilter.hasTags()) spec = spec.and(hasTags(productFilter.getTags()));
    if(productFilter.hasName()) spec = spec.and(hasName(productFilter.getName()));

    return spec;
  }

  private static Specification<ProductMO> hasProductId(Long productId){
    return ((root, query, cb) ->
      cb.equal(root.get(ProductMO_.id), productId));
  }

  private static Specification<ProductMO> hasName(String name) {
    return ((root, query, cb) ->
      cb.equal(root.get(ProductMO_.name), name));
  }


  /*public static Specification<ProductMO> hasTags(Set<String> tags){
    return ((root, query, cb) -> {

      Subquery<Long> subQuery = cb.createQuery().subquery(Long.class);
      Root<ProductMO> subRoot = subQuery.from(ProductMO.class);
      Join<ProductMO, TagMO> subTags = subRoot.join(ProductMO_.TAGS);

      subQuery.select(subRoot.get(ProductMO_.id))
        .where(cb.equal(subRoot.get(ProductMO_.id), root.get(ProductMO_.id)),
          subTags.get(TagMO_.name).in(tags));
        //.groupBy(subRoot.get(ProductMO_.id))
        //.having(cb.equal(cb.countDistinct(subTags.get(TagMO_.name)), tags.size()));

      return cb.exists(subQuery);
    });
  }*/

  private static Specification<ProductMO> empty() {
    return ((root, query, cb) -> cb.and());
  }

}
