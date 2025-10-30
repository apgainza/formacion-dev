package com.mercadona.alejandro.dev.application.services;

import com.mercadona.alejandro.dev.application.constants.I18NKeys;
import com.mercadona.alejandro.dev.application.exceptions.ProductNotFoundException;
import com.mercadona.alejandro.dev.application.ports.driven.ProductDatasourcePort;
import com.mercadona.alejandro.dev.application.ports.driving.ProductPort;
import com.mercadona.alejandro.dev.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductUseCase implements ProductPort {

  private final CodeBarService codeBarService;
  private final ProductDatasourcePort productDatabasePort;

  @Transactional(readOnly = true)
  @Override
  public Product searchProductByCodeBar(String codeBar) {

    CodeBar code = codeBarService.validate(codeBar);

    return productDatabasePort.findProductByCodeBar(code.getCodeProduct(), code.getCodeStore());

  }

  @Transactional
  @Override
  public Product createProduct(UpsertProductCommand command) {
    log.info("Save product {}", command);
    return productDatabasePort.saveProduct(command);

  }

  @Override
  public Product getProduct(Long productId) {
    return productDatabasePort.findById(productId)
      .orElseThrow(() -> new ProductNotFoundException(I18NKeys.Product.NOT_FOUND, productId));
  }

  @Transactional(readOnly = true)
  @Override
  public Page<Product> getAllProducts(Integer firstPage, Integer pageSize, String sort, String locale) {
    return productDatabasePort.findAllProducts(firstPage, pageSize, sort);
  }

  @Transactional
  @Override
  public Product updateProduct(Long id, Product product) {
    return null;
  }

  @Transactional
  @Override
  public void deleteProduct(Long productId) {
    getProduct(productId);
    // Eliminar el producto
  }
}
