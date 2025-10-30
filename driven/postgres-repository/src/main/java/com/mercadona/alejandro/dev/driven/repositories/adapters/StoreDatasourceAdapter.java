package com.mercadona.alejandro.dev.driven.repositories.adapters;

import com.mercadona.alejandro.dev.application.ports.driven.StoreDatasourcePort;
import com.mercadona.alejandro.dev.domain.Store;
import com.mercadona.alejandro.dev.domain.StoreFilter;
import com.mercadona.alejandro.dev.driven.repositories.filtering.StoreFiltering;
import com.mercadona.alejandro.dev.driven.repositories.mappers.StoreDbMapper;
import com.mercadona.alejandro.dev.driven.repositories.models.StoreRepository;
import com.mercadona.alejandro.dev.driven.repositories.models.StoreMO;
import com.mercadona.framework.cna.lib.repository.builders.MercadonaPageBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreDatasourceAdapter implements StoreDatasourcePort {

  private final MercadonaPageBuilder mercadonaPageBuilder;
  private final StoreRepository storeRepository;
  private final StoreDbMapper storeDbMapper;

  @Override
  public Page<Store> getAllStores(StoreFilter storeFilter, Integer pageNumber, Integer pageSize, String sort) {
    Pageable pageable = mercadonaPageBuilder.builder().page(pageNumber).pageSize(pageSize).sort(sort).build();
    Specification<StoreMO> process = StoreFiltering.process(storeFilter);
    return storeRepository.findAll(process, pageable)
      .map(storeDbMapper::toDomain);
  }

  @Override
  public Optional<Store> getStoreByCode(String code) {
    return storeRepository.findByCode(code)
      .map(storeDbMapper::toDomain);
  }

  @Override
  public Optional<Store> getStoreById(Long id) {
    return storeRepository.findById(id)
      .map(storeDbMapper::toDomain);
  }

  @Override
  public Store save(Store store) {
    StoreMO storeMO = storeDbMapper.fromModel(store);
    var savedStoreMO = storeRepository.save(storeMO);
    return storeDbMapper.toDomain(savedStoreMO);
  }

  @Override
  public void deleteById(Long storeId) {
    storeRepository.deleteById(storeId);
  }
}
