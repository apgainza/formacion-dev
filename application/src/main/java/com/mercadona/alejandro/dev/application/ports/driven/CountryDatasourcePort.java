package com.mercadona.alejandro.dev.application.ports.driven;

import com.mercadona.alejandro.dev.domain.Country;

import java.util.Optional;

public interface CountryDatasourcePort {

  Optional<Country> getCountryByCode(String code);

}
