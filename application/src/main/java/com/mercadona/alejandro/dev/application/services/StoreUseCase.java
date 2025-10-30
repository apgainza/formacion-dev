package com.mercadona.alejandro.dev.application.services;

import com.mercadona.alejandro.dev.application.constants.I18NKeys;
import com.mercadona.alejandro.dev.application.exceptions.StoreExistsException;
import com.mercadona.alejandro.dev.application.exceptions.StoreNotFoundException;
import com.mercadona.alejandro.dev.application.ports.driven.StoreDatasourcePort;
import com.mercadona.alejandro.dev.application.ports.driving.StorePort;
import com.mercadona.alejandro.dev.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreUseCase implements StorePort {

  private final StoreDatasourcePort storeDatasourcePort;

  @Override
  public Page<Store> searchAllStores(StoreFilter storeFilter, Integer pageNumber, Integer pageSize, String sort) {
    return storeDatasourcePort.getAllStores(storeFilter, pageNumber, pageSize, sort);
  }

  @Override
  public Store getStoreById(Long id) {
    return getStore(id);
  }

  @Override
  public Optional<Store> getStoreByCode(String code) {
    return storeDatasourcePort.getStoreByCode(code);
  }

  @Transactional
  @Override
  public Store save(Store store) {
    String code = store.getCode();
    getStoreByCode(code).ifPresent(savedStore -> {throw new StoreExistsException(I18NKeys.Store.EXISTS, code);});

    return storeDatasourcePort.save(store);
  }

  @Transactional
  @Override
  public Store update(Long storeId, Store store) {
    Store storeDB = getStore(storeId);
    storeDB.setName(store.getName());
    storeDB.setCode(store.getCode());

    return storeDatasourcePort.save(store);
  }

  @Transactional
  @Override
  public void deleteById(Long storeId) {
    getStore(storeId);

    storeDatasourcePort.deleteById(storeId);
  }

  private Store getStore(Long id) {
    return storeDatasourcePort.getStoreById(id)
      .orElseThrow(() -> new StoreNotFoundException(I18NKeys.Store.NOT_FOUND, id));
  }
}
