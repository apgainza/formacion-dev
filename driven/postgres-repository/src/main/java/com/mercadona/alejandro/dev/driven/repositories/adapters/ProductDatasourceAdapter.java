package com.mercadona.alejandro.dev.driven.repositories.adapters;

import com.mercadona.alejandro.dev.application.constants.I18NKeys;
import com.mercadona.alejandro.dev.application.exceptions.ValidationException;
import com.mercadona.alejandro.dev.application.ports.driven.ProductDatasourcePort;
import com.mercadona.alejandro.dev.domain.Product;
import com.mercadona.alejandro.dev.domain.UpsertProductCommand;
import com.mercadona.alejandro.dev.driven.repositories.ProductRepository;
import com.mercadona.alejandro.dev.driven.repositories.StoreRepository;
import com.mercadona.alejandro.dev.driven.repositories.mappers.ProductDbMapper;
import com.mercadona.alejandro.dev.driven.repositories.models.*;
import com.mercadona.framework.cna.lib.repository.builders.MercadonaPageBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductDatasourceAdapter implements ProductDatasourcePort {

  private final MercadonaPageBuilder mercadonaPageBuilder;
  private final ProductRepository productRepository;
  private final StoreRepository storeRepository;
  private final ProductDbMapper productDbMapper;

  @Override
  public Optional<Product> getProductByCode(String code) {
    return productRepository.findByCode(code)
      .map(productDbMapper::toDomain);
  }

  @Override
  public Product findProductByCodeBar(String codeProduct, String codeStore) {
    ProductMO productByCodeBar = productRepository.findProductByCodeBar(codeProduct, codeStore);
    return productDbMapper.toDomain(productByCodeBar);
  }

  @Override
  public Product saveProduct(UpsertProductCommand upsertProductCommand) {
    checkExistProduct(upsertProductCommand);

    ProductMO product = productDbMapper.fromDomain(upsertProductCommand);
    product.setAssortments(getAssortments(product, upsertProductCommand));

    return productDbMapper.toDomain(productRepository.save(product));
  }

  @Override
  public Page<Product> findAllProducts(int page, int size, String sort) {

    Pageable pageRequest = mercadonaPageBuilder.builder().page(page).pageSize(size).sort(sort).build();

    Page<ProductMO> products = productRepository.findAll(pageRequest);

    return products.map(productDbMapper::toDomain);
  }

  @Transactional(readOnly = true)
  @Override
  public Page<Product> findAllProductsJPQL(Long categoryId, int page, int size) {

    PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

    Page<ProductMO> products = productRepository.findAllByCategory(categoryId, pageRequest);

    return products.map(productDbMapper::toDomain);
  }

  @Transactional(readOnly = true)
  @Override
  public Page<Product> findAllProductsWithOutCountQuery(Long categoryId, int page, int size) {

    PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

    List<Tuple> tupleProducts = productRepository.findAllbyCategoryWithOutQueryCount(categoryId, pageRequest);
    List<Long> ids = tupleProducts.stream().map(t -> t.get(0, BigInteger.class)).map(BigInteger::longValue).toList();
    List<ProductMO> products = productRepository.findAllById(ids);

    return new PageImpl<>(products.stream().map(productDbMapper::toDomain).toList(), pageRequest, tupleProducts.size());
  }

  @Override
  public Page<Product> findAllProductsDtoByCategory(Long categoryId, int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

    return productRepository.findAllDtoByCategory(categoryId, pageRequest)
      .map(productDbMapper::toDomain);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Product> findAllProductsByProductAndStore(Long productId, String storeId) {
    return productRepository.findAllProductsByProductAndStore(storeId)
      .stream().map(productDbMapper::toDomain).toList();
  }

  @Transactional(readOnly = true)
  @Override
  public List<Product> findAllProductsByProductAndStoreV2(Long productId, String storeId) {
    return productRepository.findAllProductsByProductAndStoreV2(storeId)
      .stream().map(productDbMapper::toDomain).toList();
  }

  @Override
  public Optional<Product> findById(Long id) {
    return productRepository.findById(id).map(productDbMapper::toBasicDomain);
  }

  private void checkExistProduct(UpsertProductCommand product) {
    String codeProduct = product.getCode();
    productRepository.findByCode(codeProduct)
      .ifPresent(p -> {
        throw new ValidationException(I18NKeys.Product.EXISTS, codeProduct);
      });

    Long parentIdProduct = product.getParentIdProduct();
    if (Objects.nonNull(parentIdProduct)) {
      productRepository.findById(parentIdProduct).orElseThrow(() -> new ValidationException("Product with id " + parentIdProduct + " no exists"));
    }
  }

  private List<AssortmentMO> getAssortments(ProductMO product, UpsertProductCommand upsertProductCommand) {
    Set<String> storeIds = upsertProductCommand.getStoreIds();
    if(Objects.isNull(storeIds)){
      return List.of();
    }
    return storeIds.stream()
      .map(storeCode -> Pair.of(storeCode, storeRepository.findByCode(storeCode)))
      .map(sd -> sd.getRight().orElseThrow(() -> new EntityNotFoundException("Store with id " + sd.getLeft() + " not found")))
      .map(store -> AssortmentMO.builder()
        .id(AssortmentPk.builder()
          .productId(product.getId())
          .storeId(store.getId())
          .build())
        .store(store)
        .product(product)
        .build())
      .toList();
  }
}
