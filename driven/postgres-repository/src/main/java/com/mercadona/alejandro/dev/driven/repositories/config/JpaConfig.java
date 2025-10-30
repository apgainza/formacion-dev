package com.mercadona.alejandro.dev.driven.repositories.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.mercadona.alejandro.dev.driven.repositories"})
public class JpaConfig {

}
