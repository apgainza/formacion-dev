package com.mercadona.alejandro.dev.driven.repositories;

import com.mercadona.alejandro.dev.domain.ProductFilter;
import com.mercadona.alejandro.dev.driven.repositories.filtering.ProductFiltering;
import com.mercadona.alejandro.dev.driven.repositories.models.CommentsMO;
import com.mercadona.alejandro.dev.driven.repositories.models.ProductMO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StopWatch;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@DataJpaTest
@ContextConfiguration(classes = TestJpaConfigH2.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private TransactionTemplate transactionTemplate;

  @Autowired
  private EntityManager em;


  @Test
  @Sql("/test-data.sql")
  void shouldGetProductWithOutTagsWithSpecificationsAndSubselect() {

    ProductFilter productFilter = ProductFilter.builder()
      .productId(1L)
      .build();

    List<ProductMO> products = productRepository.findAll(ProductFiltering.process(productFilter));

    assertEquals(1, products.size());

  }

  @Test
  @Sql("/test-data.sql")
  void shouldGetProductWithTagsWithSpecificationsAndSubselect() {

    ProductFilter productFilter = ProductFilter.builder()
      .productId(1L)
      .tags(Set.of("Tag 1", "Tag 2"))
      .build();

    List<ProductMO> products = productRepository.findAll(ProductFiltering.process(productFilter));

    assertEquals(1, products.size());

  }


  /*@Test
  @Sql("/test-data.sql")
  void shouldSaveProduct() {

    *//*transactionTemplate.execute(status -> {

      ProductMO productDB = saveProduct();

      saveTags(productDB);

      saveComments(productDB);

      return productDB;

    });*//*


    ProductFilter productFilter = ProductFilter.builder()
      .tags(Set.of("Tag 1", "Tag 2"))
      .build();


    StopWatch timer = new StopWatch();

    timer.start();
    Specification<ProductMO> spec = ProductFiltering.process(productFilter);
    List<ProductMO> product = productRepository.findAll(spec);
    //Specification<Object> spec = ProductFiltering.custom(1L, Set.of("Tag 1", "Tag 2"));
    // Object product = productMOJpaRepository.findAll(spec, TagMO.class);
    timer.stop();

    log.info("Specification to get the product {} in {}", product, timer.getTotalTimeSeconds());

    List<ProductMO> resultList = em.createQuery("""
      SELECT p from ProductMO p
       INNER JOIN p.tags t
       where p.id =1
      """, ProductMO.class).getResultList();
    timer.start();
    timer.stop();

    log.info("JPQL to get the product {} in {}", product, timer.getTotalTimeSeconds());

    *//*timer.start();
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
    assertEquals(12L, countFiles);*//*

  }*/

 /* private Long getNumberOfFiles() {
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
  }*/

  /*private void saveTags(ProductMO productDB) {
    for (int i = 0; i < 10000; i++) {

      em.persist(TagMO.builder()
        .product(productDB)
        .name("Tag " + i)
        .build());

    }
  }*/

  private ProductMO saveProduct() {
    return productRepository.save(ProductMO.builder()
      .name("Product 1")
      .build());
  }


}
