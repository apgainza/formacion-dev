package com.mercadona.alejandro.dev.driven.repositories.projections;

import com.mercadona.alejandro.dev.driven.repositories.models.StoreMO;

public record StoreProjection(Long id, String name, String code, Long idCountry, String countryCode) {
}
