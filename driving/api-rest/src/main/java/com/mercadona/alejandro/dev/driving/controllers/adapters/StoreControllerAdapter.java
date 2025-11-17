package com.mercadona.alejandro.dev.driving.controllers.adapters;

import com.mercadona.alejandro.dev.application.ports.driving.StorePort;
import com.mercadona.alejandro.dev.domain.Store;
import com.mercadona.alejandro.dev.domain.StoreFilter;
import com.mercadona.alejandro.dev.driving.controllers.adapters.api.StoresApi;
import com.mercadona.alejandro.dev.driving.controllers.adapters.model.StoreFilterRequest;
import com.mercadona.alejandro.dev.driving.controllers.adapters.model.StorePageResponse;
import com.mercadona.alejandro.dev.driving.controllers.adapters.model.StoreRequest;
import com.mercadona.alejandro.dev.driving.controllers.adapters.model.StoreResponse;
import com.mercadona.alejandro.dev.driving.controllers.mappers.StoreDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StoreControllerAdapter implements StoresApi {

  private final StorePort storePort;
  private final StoreDTOMapper storeDTOMapper;

  @Override
  public ResponseEntity<StorePageResponse> searchStores(@Valid StoreFilterRequest storeFilterRequest, @Valid Integer firstPage, @Valid Integer pageSize, @Valid String sort) {
    StoreFilter storeFilter = storeDTOMapper.toDomain(storeFilterRequest);
    Page<Store> stores = storePort.searchAllStores(storeFilter, firstPage, pageSize, sort);
    return ResponseEntity.ok(storeDTOMapper.fromPageRequest(stores));
  }

  @Override
  public ResponseEntity<StoreResponse> getStore(Long storeId) {
    return ResponseEntity.ok(storeDTOMapper.fromDomain(storePort.getStoreById(storeId)));
  }

  @Override
  public ResponseEntity<StoreResponse> createStore(@Valid StoreRequest storeRequest) {
    Store store = storeDTOMapper.toDomain(storeRequest);
    store = storePort.save(store);
    return ResponseEntity.ok(storeDTOMapper.fromDomain(store));
  }

  @Override
  public ResponseEntity<StoreResponse> updateStore(Long storeId, StoreRequest storeRequest) {
    Store store = storePort.update(storeId, storeDTOMapper.toDomain(storeRequest));
    return ResponseEntity.ok(storeDTOMapper.fromDomain(store));
  }

  @Override
  public ResponseEntity<Void> deleteStore(Long storeId) {
    storePort.deleteById(storeId);
    return ResponseEntity.noContent().build();
  }
}


