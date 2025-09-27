package com.mercadona.alejandro.dev.application.ports.driven;

import com.mercadona.framework.cna.commons.interfaces.CNACrudRepository;
import com.mercadona.alejandro.dev.domain.Example;

import java.util.Optional;

public interface ExampleRepositoryPort extends CNACrudRepository<Example, Long> {
  Optional<Example> findExampleByNameAndNumberOfDaysInWeek(String name, int numberOfDaysInWeek);
}
