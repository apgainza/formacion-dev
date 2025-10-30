package com.mercadona.alejandro.dev;

import com.mercadona.alejandro.dev.application.ports.driven.ProductDatasourcePort;
import com.mercadona.alejandro.dev.application.services.ProductUseCase;
import com.mercadona.alejandro.dev.domain.Product;
import com.mercadona.alejandro.dev.driven.repositories.StudentMOJpaRepository;
import com.mercadona.alejandro.dev.driven.repositories.models.CourseMO;
import com.mercadona.alejandro.dev.driven.repositories.models.EnrolmentMO;
import com.mercadona.alejandro.dev.driven.repositories.models.StudentMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDateTime;

// @Configuration
@Slf4j
public class Runner implements CommandLineRunner {

  @Autowired
  private StudentMOJpaRepository studentMOJpaRepository;

  @Autowired
  private ProductUseCase productUseCase;

  @Autowired
  private ProductDatasourcePort productDatabasePort;


  @Override
  public void run(String... args) throws Exception {

    // List<Product> allProductsByProductAndStoreV2 = productDatabasePort.findAllProductsByProductAndStoreV2(1L, "003117");

    // List<Product> allProductsByProductAndStore = productDatabasePort.findAllProductsByProductAndStore(1L, "003117");


    // Page<Product> allProducts = productDatabasePort.findAllProducts(1, 3);

    // Page<Product> allProductsDtoByCategory = productDatabasePort.findAllProductsDtoByCategory(1L, 1, 1);

    // Page<Product> allProductsJPQL = productDatabasePort.findAllProductsWithOutCountQuery(1L, 0, 3);

    String barCode = "081-003117-12232390";

    Product product = productUseCase.searchProductByCodeBar(barCode);

    /*UpsertProductCommand upsertProductCommand = UpsertProductCommand.builder()
      .categoryId(1L)
      .codeProduct("12232392")
      .nameProduct("Pack de 6 botellas de agua 1.5L")
      .parentIdProduct(1L)
      .storeIds(Set.of("003117"))
      .quantity(2)
      .state(ProductType.ACTIVE)
      .build();

    productUseCase.createProduct(upsertProductCommand);*/



    //testStudents();

  }

  private void testStudents() {
    StudentMO student = StudentMO.builder()
            .name("Alejandro")
            .build();

    CourseMO course = CourseMO.builder()
      .name("Matemáticas")
      .build();

    EnrolmentMO enrolmentMO = EnrolmentMO.builder()
      .createdAt(LocalDateTime.now())
      .student(student)
      .course(course)
      .build();

    student.addEnrolment(enrolmentMO, course);


    studentMOJpaRepository.save(student);
  }
}
