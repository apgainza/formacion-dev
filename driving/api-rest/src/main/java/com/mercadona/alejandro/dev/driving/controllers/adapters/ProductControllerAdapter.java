package com.mercadona.alejandro.dev.driving.controllers.adapters;

import com.mercadona.alejandro.dev.application.ports.driving.ProductPort;
import com.mercadona.alejandro.dev.domain.Product;
import com.mercadona.alejandro.dev.driving.controllers.adapters.api.ProductsApi;
import com.mercadona.alejandro.dev.driving.controllers.adapters.model.ProductPageResponse;
import com.mercadona.alejandro.dev.driving.controllers.adapters.model.ProductRequest;
import com.mercadona.alejandro.dev.driving.controllers.adapters.model.ProductResponse;
import com.mercadona.alejandro.dev.driving.controllers.mappers.ProductDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductControllerAdapter implements ProductsApi {

  private final ProductPort productPort;
  private final ProductDTOMapper productDTOMapper;

  @Override
  public ResponseEntity<ProductResponse> getCodeBarProduct(String codeBar, @Pattern(regexp = "^[a-z]{2}([_-][A-Z]{2})?$") @Valid String locale) {
    Product product = productPort.searchProductByCodeBar(codeBar);
    return ResponseEntity.ok(productDTOMapper.fromDomain(product));
  }

  @Override
  public ResponseEntity<ProductPageResponse> getProductCollection(@Valid Integer firstPage, @Valid Integer pageSize, @Valid String sort) {
    Page<Product> products = productPort.getAllProducts(firstPage, pageSize, sort, null);
    return ResponseEntity.ok(productDTOMapper.fromPageRequest(products));
  }

  @Override
  public ResponseEntity<ProductResponse> getProductById(Long productId, @Pattern(regexp = "^[a-z]{2}([_-][A-Z]{2})?$") @Valid String locale) {
    Product product = productPort.getProduct(productId);
    return ResponseEntity.ok(productDTOMapper.fromDomain(product));
  }

  @Override
  public ResponseEntity<ProductResponse> createProduct(@Valid ProductRequest productRequest) {
    Product product = productPort.createProduct(productDTOMapper.toDomain(productRequest));
    return ResponseEntity.status(HttpStatus.CREATED).body(productDTOMapper.fromDomain(product));
  }
}


