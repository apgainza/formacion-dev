package com.mercadona.alejandro.dev.driven.repositories.adapters;

import java.util.List;
import java.util.Optional;

import com.mercadona.alejandro.dev.driven.repositories.models.ExampleMO;
import com.mercadona.framework.cna.commons.domain.MercadonaPage;
import com.mercadona.framework.cna.lib.repository.builders.MercadonaPageBuilder;
import org.hibernate.sql.ordering.antlr.SortSpecification;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mercadona.alejandro.dev.domain.Example;
import com.mercadona.alejandro.dev.driven.repositories.mappers.ExampleMapper;
import com.mercadona.alejandro.dev.driven.repositories.ExampleMOJpaRepository;
import com.mercadona.alejandro.dev.application.ports.driven.ExampleRepositoryPort;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ExampleRepositoryAdapter implements ExampleRepositoryPort {

  private final MercadonaPageBuilder mercadonaPageBuilder;

  private final ExampleMOJpaRepository repository;

  private final ExampleMapper mapper;

  @Override
  public Optional<Example> findById(Long id) {

    var exampleMO = repository.findById(id);

    return mapper.fromOptionalModel(exampleMO);

  }

  @Override
  public MercadonaPage<Example> findAll(Integer pageNumber, Integer pageSize, String sort) {

    var pageRequest =  mercadonaPageBuilder.builder().page(pageNumber).pageSize(pageSize).sort(sort).build();

    var exampleMOs = repository.findAll(pageRequest);

    return mapper.fromModels(exampleMOs);

  }

  @Override
  public Example save(Example example) {

    var exampleModel = mapper.toModel(example);

    var exampleSaved = repository.save(exampleModel);

    return mapper.fromModel(exampleSaved);

  }

  @Override
  public void deleteById(Long id) {

    repository.deleteById(id);

  }

  @Override
  public Optional<Example> findExampleByNameAndNumberOfDaysInWeek(String name, int numberOfDaysInWeek) {

    List<ExampleMO> name1 = repository.findAll(Sort.by("name").ascending());

    Optional<Example> exampleByNameEquals = repository.findExampleByNameLike(name);

    return repository.findExampleByNameAndNumberOfDaysInWeekEquals(name, numberOfDaysInWeek);
  }
}
