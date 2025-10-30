package com.mercadona.alejandro.dev.application.services;

import com.mercadona.alejandro.dev.application.exceptions.ValidationException;
import com.mercadona.alejandro.dev.application.ports.driven.CountryDatasourcePort;
import com.mercadona.alejandro.dev.application.ports.driven.ProductDatasourcePort;
import com.mercadona.alejandro.dev.application.ports.driven.StoreDatasourcePort;
import com.mercadona.alejandro.dev.application.constants.I18NKeys;
import com.mercadona.alejandro.dev.domain.*;
import com.mercadona.framework.cna.commons.domain.MercadonaBusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class CodeBarService {

  private final CountryDatasourcePort countryDatasourcePort;
  private final StoreDatasourcePort storeDatasourcePort;
  private final ProductDatasourcePort productDatabasePort;

  public CodeBar validate(String codeBar) {

    Pattern pattern = Pattern.compile("^(\\d{2})-(\\d{6})-(\\d{8})$");
    Matcher matcher = pattern.matcher(codeBar);

    if (!matcher.matches()){
      log.warn("Invalid code bar {}", codeBar);
      throw new ValidationException(I18NKeys.CodeBar.INVALID, codeBar);
    }

    String codeCountry = matcher.group(1);
    String codeStore = matcher.group(2);
    String codeProduct = matcher.group(3);

    getCountry(codeCountry);
    Store store = getStore(codeStore);
    getProduct(codeProduct);

    validateCodeBar(codeCountry, store);

    return CodeBar.builder()
      .codeCountry(codeCountry)
      .codeStore(store.getCode())
      .codeProduct(codeProduct)
      .build();

  }

  private void validateCodeBar(String codeCountry, Store store) {
    if(!codeCountry.equals(store.getCountryCode())){
      log.warn("The code country {} do not belong to the store {}", codeCountry, store.getCode());
      throw new MercadonaBusinessException("The code country " + codeCountry + " do not belong to the store " + store.getCode());
    }
  }

  private void getCountry(String codeCountry) {
    countryDatasourcePort.getCountryByCode(codeCountry)
      .orElseThrow(() -> new MercadonaBusinessException("Country with code " + codeCountry + " not found"));
  }

  private Store getStore(String codeStore) {
    return storeDatasourcePort.getStoreByCode(codeStore)
      .orElseThrow(() -> new MercadonaBusinessException("Store with code " + codeStore + " not found"));
  }

  private void getProduct(String productCode) {
    productDatabasePort.getProductByCode(productCode)
      .orElseThrow(() -> new MercadonaBusinessException("Product with code " + productCode + " not found"));
  }
}
