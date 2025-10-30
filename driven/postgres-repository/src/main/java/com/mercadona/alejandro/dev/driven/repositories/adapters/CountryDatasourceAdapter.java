package com.mercadona.alejandro.dev.driven.repositories.adapters;

import com.mercadona.alejandro.dev.application.ports.driven.CountryDatasourcePort;
import com.mercadona.alejandro.dev.domain.Country;
import com.mercadona.alejandro.dev.driven.repositories.CountryRepository;
import com.mercadona.alejandro.dev.driven.repositories.mappers.CountryDbMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryDatasourceAdapter implements CountryDatasourcePort {

  private final CountryRepository countryJpaRepository;
  private final CountryDbMapper countryDbMapper;


  @Override
  public Optional<Country> getCountryByCode(String code) {
    return countryJpaRepository.findByCode(code)
      .map(countryDbMapper::toDomain);
  }
}
