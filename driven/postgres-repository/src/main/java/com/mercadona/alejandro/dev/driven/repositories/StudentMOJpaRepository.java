package com.mercadona.alejandro.dev.driven.repositories;

import com.mercadona.alejandro.dev.driven.repositories.models.StudentMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMOJpaRepository extends JpaRepository<StudentMO, Long> {

}
