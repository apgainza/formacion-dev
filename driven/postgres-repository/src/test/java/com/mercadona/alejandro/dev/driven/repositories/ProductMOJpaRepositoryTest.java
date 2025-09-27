package com.mercadona.alejandro.dev.driven.repositories;

import com.mercadona.alejandro.dev.domain.ProductFilter;
import com.mercadona.alejandro.dev.driven.repositories.filtering.ProductFiltering;
import com.mercadona.alejandro.dev.driven.repositories.models.CommentsMO;
import com.mercadona.alejandro.dev.driven.repositories.models.ProductMO;
import com.mercadona.alejandro.dev.driven.repositories.models.TagMO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@DataJpaTest
@ContextConfiguration(classes = TestJpaConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class ProductMOJpaRepositoryTest {

  @Autowired
  private ProductMOJpaRepository productMOJpaRepository;

  @Autowired
  private TransactionTemplate transactionTemplate;

  @Autowired
  private EntityManager em;

  @Test
  void shouldSaveProduct() {

    transactionTemplate.execute(status -> {

      ProductMO productDB = saveProduct();

      saveTags(productDB);

      saveComments(productDB);

      return productDB;

    });


    ProductFilter productFilter = ProductFilter.builder()
      .tags(Set.of("Tag 1", "Tag 2"))
      .build();

    Specification<ProductMO> spec = ProductFiltering.process(productFilter);
    productMOJpaRepository.findAll(spec)


    /*StopWatch timer = new StopWatch();

    timer.start();
    long countProducts = productMOJpaRepository.count();
    timer.stop();

    log.info("[{}] seconds in get the product with tags and comments.", timer.getTotalTimeSeconds());

    timer.start();
    Long countFiles = getNumberOfFiles();
    timer.stop();

    log.info("[{}] seconds in get the {} files product with tags and comments.", timer.getTotalTimeSeconds(), countFiles);

    timer.start();
    List<ProductMO> count = productMOJpaRepository.findProductsById(1L);
    timer.stop();

    log.info("[{}] seconds in get the {} products with tags", timer.getTotalTimeSeconds(), count.size());


    timer.start();
    List<ProductMO> countHints = productMOJpaRepository.findProductsByIdWithHints(1L);
    timer.stop();

    log.info("[{}] seconds in get the {} products hints with tags", timer.getTotalTimeSeconds(), countHints.size());

    assertEquals(1L, countProducts);
    assertEquals(12L, countFiles);*/

  }

  private Long getNumberOfFiles() {
    // Contamos las filas SQL del producto cartesiano
    return em.createQuery(
        "select count(*) from ProductMO o join o.tags t join o.comments c", Long.class)
      .getSingleResult();
  }

  private void saveComments(ProductMO productDB) {
    for (int i = 0; i < 10000; i++) {

      em.persist(CommentsMO.builder()
        .product(productDB)
        .name("Comment " + i)
        .build());

    }
  }

  private void saveTags(ProductMO productDB) {
    for (int i = 0; i < 10000; i++) {

      em.persist(TagMO.builder()
        .product(productDB)
        .name("Tag " + i)
        .build());

    }
  }

  private ProductMO saveProduct() {
    return productMOJpaRepository.save(ProductMO.builder()
      .name("Product 1")
      .build());
  }


}
