package com.mercadona.alejandro.dev.driven.repositories;

import com.mercadona.alejandro.dev.domain.Example;
import com.mercadona.alejandro.dev.driven.repositories.models.ExampleMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExampleMOJpaRepository extends JpaRepository<ExampleMO, Long> {

  @Query(value = """
    SELECT * FROM o_examples
    WHERE name = :nombre
    """, nativeQuery = true)
  Example findExampleByName(@Param("nombre") String name);

  Optional<Example> findExampleByNameLike(String name);

  Optional<Example> findExampleByNameAndNumberOfDaysInWeekEquals(String name, int numberOfDaysInWeek);
}
