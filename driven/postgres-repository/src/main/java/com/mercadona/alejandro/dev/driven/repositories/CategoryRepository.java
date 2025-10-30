package com.mercadona.alejandro.dev.driven.repositories;

import com.mercadona.alejandro.dev.driven.repositories.models.CategoryMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryMO, Long> {

}
