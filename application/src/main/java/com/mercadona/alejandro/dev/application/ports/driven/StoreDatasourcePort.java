package com.mercadona.alejandro.dev.application.ports.driven;

import com.mercadona.alejandro.dev.domain.Store;
import com.mercadona.alejandro.dev.domain.StoreFilter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StoreDatasourcePort {

  Page<Store> getAllStores(StoreFilter storeFilter, Integer pageNumber, Integer pageSize, String sort);

  Optional<Store> getStoreById(Long id);

  Page<Store> findAllStores(StoreFilter storeFilter, Integer pageNumber, Integer pageSize, String sort);

  Page<Store> findAllStoresProjections(StoreFilter storeFilter, Integer pageNumber, Integer pageSize, String sort);

  Optional<Store> getStoreByCode(String code);

  Store save(Store store);


  void deleteById(Long storeId);

}
