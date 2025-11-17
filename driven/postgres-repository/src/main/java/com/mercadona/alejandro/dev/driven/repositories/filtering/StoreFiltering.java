package com.mercadona.alejandro.dev.driven.repositories.filtering;

import com.mercadona.alejandro.dev.domain.StoreFilter;
import com.mercadona.alejandro.dev.driven.repositories.models.*;
import com.mercadona.codehut.jpa.QueryUtils;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;

@UtilityClass
public class StoreFiltering {

  public static Specification<StoreMO> process(StoreFilter storeFilter) {
    Specification<StoreMO> spec = empty();

    if (StringUtils.isNotBlank(storeFilter.name())) {
      spec = spec.and(getNameFilter(storeFilter.name()));
    }

    if (StringUtils.isNotBlank(storeFilter.code())) {
      spec = spec.and(getCodeFilter(storeFilter.code()));
    }

    if(StringUtils.isNotBlank(storeFilter.country())){
      spec = spec.and(getCountryFilter(storeFilter.country()));
    }

    if (StringUtils.isNotBlank(storeFilter.product())) {
      spec = spec.and(getProductFilter(storeFilter.product()));
    }

    return spec;
  }

  private static Specification<StoreMO> getNameFilter(String name) {
    return (root, query, cb) -> {
      String escapedName = escapeLikePattern(name.toLowerCase());
      return cb.like(cb.lower(root.get(StoreMO_.NAME)), "%" + escapedName + "%", '\\');
    };
  }

  private static Specification<StoreMO> getCodeFilter(String code) {
    return (root, query, cb) ->
      cb.equal(root.get(StoreMO_.CODE), code);
  }

  private static Specification<StoreMO> getCountryFilter(String country) {
    return (root, query, cb) -> {
      Join<StoreMO, CountryMO> countryJoin = QueryUtils.join(root, StoreMO_.country, JoinType.INNER);
      String escapedCountry = escapeLikePattern(country.toLowerCase());
      return cb.like(cb.lower(countryJoin.get(CountryMO_.NAME)), "%" + escapedCountry + "%", '\\');
    };
  }

  private static Specification<StoreMO> getProductFilter(String code) {
    return (root, query, cb) -> {
      // Join: Store -> Assortments -> Product
      ListJoin<StoreMO, AssortmentMO> assortmentsJoin = QueryUtils.join(root, StoreMO_.assortments, JoinType.LEFT);
      Join<AssortmentMO, ProductMO> productJoin = QueryUtils.join(assortmentsJoin, AssortmentMO_.product, JoinType.LEFT);

      // Filtrar por nombre del producto (LIKE insensible a mayúsculas)
      String escapedCode = escapeLikePattern(code.toLowerCase());
      return cb.like(cb.lower(productJoin.get("code")), "%" + escapedCode + "%", '\\');
    };
  }

  private static Specification<StoreMO> empty() {
    return (root, query, cb) -> cb.and();
  }

  private static String escapeLikePattern(String input) {
    return input
      .replace("\\", "\\\\")
      .replace("%", "\\%")
      .replace("_", "\\_");
  }
}
