package com.mercadona.alejandro.dev.driven.repositories.mappers;

import com.mercadona.alejandro.dev.domain.Country;
import com.mercadona.alejandro.dev.domain.Store;
import com.mercadona.alejandro.dev.driven.repositories.models.CountryMO;
import com.mercadona.alejandro.dev.driven.repositories.models.StoreMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CountryDbMapper.class)
public interface StoreDbMapper {

  @Mapping(target = "countryCode", source = "country.code")
  Store toDomain(StoreMO storeMO);

  StoreMO fromModel(Store store);
}
