package com.mercadona.alejandro.dev.driven.repositories.adapters;

import com.mercadona.alejandro.dev.application.ports.driven.CategoryDatasourcePort;
import com.mercadona.alejandro.dev.domain.Category;
import com.mercadona.alejandro.dev.driven.repositories.CategoryRepository;
import com.mercadona.alejandro.dev.driven.repositories.mappers.CategoryDbMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryDatasourceAdapter implements CategoryDatasourcePort {

  private final CategoryRepository categoryRepository;
  private final CategoryDbMapper categoryDbMapper;

  @Override
  public Optional<Category> getCategoryById(Long id) {
    return categoryRepository.findById(id)
      .map(categoryDbMapper::toDomain);
  }
}
