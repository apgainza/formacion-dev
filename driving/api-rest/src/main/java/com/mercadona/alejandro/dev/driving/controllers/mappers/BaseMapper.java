package com.mercadona.alejandro.dev.driving.controllers.mappers;

import com.mercadona.alejandro.dev.driving.controllers.adapters.model.PaginationResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface BaseMapper {

  default PaginationResponse mapPagination(Page<?> src) {
    return PaginationResponse.builder()
      .requestedPage(src.getNumber() + 1)
      .requestedSize(src.getSize())
      .retrievedResults(src.getNumberOfElements())
      .totalResults(src.getTotalElements())
      .build();
  }

  default <T, S> List<T> mapContent(Page<S> src, Function<S, T> mapper) {
    return src.getContent().stream()
      .map(mapper)
      .toList();
  }

}
