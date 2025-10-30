package com.mercadona.alejandro.dev.driven.repositories.mappers;

import com.mercadona.alejandro.dev.domain.Product;
import com.mercadona.alejandro.dev.domain.Store;
import com.mercadona.alejandro.dev.domain.UpsertProductCommand;
import com.mercadona.alejandro.dev.driven.repositories.models.AssortmentMO;
import com.mercadona.alejandro.dev.driven.repositories.models.ProductDTO;
import com.mercadona.alejandro.dev.driven.repositories.models.ProductMO;
import com.mercadona.alejandro.dev.driven.repositories.models.StoreMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryDbMapper.class, StoreDbMapper.class})
public abstract class ProductDbMapper {

  @Autowired
  private StoreDbMapper storeDbMapper;

  @Mapping(target = "children", source = "children", qualifiedByName = "toBasicDomain")
  @Mapping(target = "parent", source = "parent", qualifiedByName = "toBasicDomain")
  @Mapping(target = "stores", source = "assortments", qualifiedByName = "pepe")
  public abstract Product toDomain(ProductMO productMO);

  public abstract Product toDomain(ProductDTO productDTO);

  @Named(value = "toBasicDomain")
  @Mapping(target = "children", ignore = true)
  @Mapping(target = "parent", ignore = true)
  public abstract Product toBasicDomain(ProductMO productMO);

  @Mapping(target = "category.id", source = "categoryId")
  @Mapping(target = "parent.id", source = "parentIdProduct")
  public abstract ProductMO fromDomain(UpsertProductCommand product);

  @Named("pepe")
  List<Store> toDomain(List<AssortmentMO> assortmentMOS) {
    if (assortmentMOS == null || assortmentMOS.isEmpty()) {
      return List.of();
    }
    return assortmentMOS.stream().map(assortment -> storeDbMapper.toDomain(assortment.getStore())).toList();
  }
}
