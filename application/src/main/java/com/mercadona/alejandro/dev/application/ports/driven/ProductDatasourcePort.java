package com.mercadona.alejandro.dev.application.ports.driven;

import com.mercadona.alejandro.dev.domain.Product;
import com.mercadona.alejandro.dev.domain.UpsertProductCommand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductDatasourcePort {

  Optional<Product> getProductByCode(String code);

  Product findProductByCodeBar(String codeProduct, String codeStore);

  Product saveProduct(UpsertProductCommand product);

  Page<Product> findAllProducts(int page, int size, String sort);

  Page<Product> findAllProductsJPQL(Long categoryId, int page, int size);

  Page<Product> findAllProductsWithOutCountQuery(Long categoryId, int page, int size);

  Page<Product> findAllProductsDtoByCategory(Long categoryId, int page, int size);

  List<Product> findAllProductsByProductAndStore (Long productId, String storeId);

  List<Product> findAllProductsByProductAndStoreV2 (Long productId, String storeId);

    Optional<Product> findById(Long id);
}
