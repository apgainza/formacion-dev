package com.mercadona.alejandro.dev;

import com.mercadona.alejandro.dev.application.ports.driven.ExampleRepositoryPort;
import com.mercadona.alejandro.dev.domain.Example;
import com.mercadona.alejandro.dev.domain.IdentificationTypesEnum;
import com.mercadona.alejandro.dev.driven.repositories.ExampleMOJpaRepository;
import com.mercadona.alejandro.dev.driven.repositories.models.ExampleMO;
import com.mercadona.alejandro.dev.driven.repositories.models.IdentificationTypesMOEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Component
public class RunnerTest implements CommandLineRunner {

  @Autowired
  private TransactionTemplate transactionTemplate;

  @Autowired
  private ExampleMOJpaRepository exampleRepositoryPort;

  @Autowired
  private EntityManager em;

  @Override
  public void run(String... args) throws Exception {


    transactionTemplate.executeWithoutResult(result -> {
      // Your transactional code here
      System.out.println("Executing transactional code...");

      ExampleMO example = ExampleMO.builder()
        .id(1L)
        .name("Esto es una prueba")
        .description("Comprobar los métodos equals y hashcode")
        .identificationType(IdentificationTypesMOEnum.DNI)
        .build();

      ExampleMO example1 = ExampleMO.builder()
        .name("Esto es una prueba")
        .description("Comprobar los métodos equals y hashcode")
        .identificationType(IdentificationTypesMOEnum.DNI)
        .build();


      Optional<Example> pepe = exampleRepositoryPort.findExampleByNameLike("PEPE");

      em.persist(example1);
      em.flush();

      ExampleMO proxy = em.find(ExampleMO.class, example1.getId());

      boolean equals = example.equals(proxy);


      System.out.println("Are the two examples equal? " + equals);


      result.setRollbackOnly();

    });

  }

  @PostConstruct
  public void checkProxyType() {
    System.out.println("Bean real: " + exampleRepositoryPort.getClass());
  }
}
