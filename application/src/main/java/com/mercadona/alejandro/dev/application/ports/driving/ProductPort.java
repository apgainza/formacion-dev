package com.mercadona.alejandro.dev.application.ports.driving;

import com.mercadona.alejandro.dev.domain.Product;
import com.mercadona.alejandro.dev.domain.UpsertProductCommand;
import com.mercadona.framework.cna.commons.domain.MercadonaPage;
import org.springframework.data.domain.Page;

public interface ProductPort {

  Product searchProductByCodeBar(String codeBar);
  Product createProduct(UpsertProductCommand command);
  Product getProduct(Long id);
  Page<Product> getAllProducts(Integer firstPage, Integer pageSize, String sort, String locale);
  Product updateProduct(Long id, Product product);
  void deleteProduct(Long id);
}
