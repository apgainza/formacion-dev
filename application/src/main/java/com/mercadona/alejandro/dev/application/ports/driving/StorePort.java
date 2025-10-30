package com.mercadona.alejandro.dev.application.ports.driving;

import com.mercadona.alejandro.dev.domain.Store;
import com.mercadona.alejandro.dev.domain.StoreFilter;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface StorePort {

  Page<Store> searchAllStores(StoreFilter storeFilter, Integer firstPage, Integer pageSize, String sort);

  Store getStoreById(Long id);

  Optional<Store> getStoreByCode(String code);

  Store save(Store store);

  Store update(Long storeId, Store store);

  void deleteById(Long storeId);
}
