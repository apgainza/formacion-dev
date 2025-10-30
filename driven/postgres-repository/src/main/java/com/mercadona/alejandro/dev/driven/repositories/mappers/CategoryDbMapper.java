package com.mercadona.alejandro.dev.driven.repositories.mappers;

import com.mercadona.alejandro.dev.domain.Category;
import com.mercadona.alejandro.dev.driven.repositories.models.CategoryMO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryDbMapper {

  Category toDomain(CategoryMO categoryMO);

}
