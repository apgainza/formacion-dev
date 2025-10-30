package com.mercadona.alejandro.dev.application.ports.driven;

import com.mercadona.alejandro.dev.domain.Category;

import java.util.Optional;

public interface CategoryDatasourcePort {

  Optional<Category> getCategoryById(Long id);

}
