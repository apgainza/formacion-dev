package com.mercadona.alejandro.dev.driving.controllers.mappers;

import com.mercadona.alejandro.dev.application.constants.I18NKeys;
import com.mercadona.alejandro.dev.domain.Product;
import com.mercadona.alejandro.dev.domain.UpsertProductCommand;
import com.mercadona.alejandro.web_dev.model.ProductPageResponse;
import com.mercadona.alejandro.web_dev.model.ProductRequest;
import com.mercadona.alejandro.web_dev.model.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import javax.validation.Valid;

@Mapper(componentModel = "spring")
public interface ProductDTOMapper extends BaseMapper{

  Product toDomain(ProductResponse productResponse);

  ProductResponse fromDomain(Product product);

  default ProductPageResponse fromPageRequest(Page<Product> page){
    return ProductPageResponse.builder()
      .data(mapContent(page, this::fromDomain))
      .pagination(mapPagination(page))
      .build();
  }

  @Mapping(target = "categoryId", source = "category")
  UpsertProductCommand toDomain(@Valid ProductRequest productRequest);
}
