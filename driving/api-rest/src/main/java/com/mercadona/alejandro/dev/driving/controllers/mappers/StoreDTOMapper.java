package com.mercadona.alejandro.dev.driving.controllers.mappers;


import com.mercadona.alejandro.dev.domain.Store;
import com.mercadona.alejandro.dev.domain.StoreFilter;
import com.mercadona.alejandro.dev.driving.controllers.adapters.model.StoreFilterRequest;
import com.mercadona.alejandro.dev.driving.controllers.adapters.model.StorePageResponse;
import com.mercadona.alejandro.dev.driving.controllers.adapters.model.StoreRequest;
import com.mercadona.alejandro.dev.driving.controllers.adapters.model.StoreResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface StoreDTOMapper extends BaseMapper{

  StoreFilter toDomain(StoreFilterRequest storeFilterRequest);

  @Mapping(target = "country.id", source = "country")
  @Mapping(target = "idCountry", source = "country")
  Store toDomain(StoreRequest storeRequest);

  StoreResponse fromDomain(Store store);

  default StorePageResponse fromPageRequest(Page<Store> page){
    return StorePageResponse.builder()
      .data(mapContent(page, this::fromDomain))
      .pagination(mapPagination(page))
      .build();
  }

}
