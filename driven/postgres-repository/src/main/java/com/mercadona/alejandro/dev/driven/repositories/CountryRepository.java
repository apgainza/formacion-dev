package com.mercadona.alejandro.dev.driven.repositories;

import com.mercadona.alejandro.dev.driven.repositories.models.CountryMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<CountryMO, Long> {

  @Query("""
    SELECT c FROM CountryMO c WHERE c.code = :code
""")
  Optional<CountryMO> findByCode(String code);
}
