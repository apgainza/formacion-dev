package com.mercadona.alejandro.dev.driven.repositories.mappers;

import com.mercadona.alejandro.dev.domain.Category;
import com.mercadona.alejandro.dev.domain.Country;
import com.mercadona.alejandro.dev.driven.repositories.models.CategoryMO;
import com.mercadona.alejandro.dev.driven.repositories.models.CountryMO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryDbMapper {

  Country toDomain(CountryMO countryMO);

  CountryMO fromModel(Country country);

}
