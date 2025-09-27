package com.mercadona.alejandro.dev;

import com.mercadona.alejandro.dev.driven.repositories.StudentMOJpaRepository;
import com.mercadona.alejandro.dev.driven.repositories.models.CourseMO;
import com.mercadona.alejandro.dev.driven.repositories.models.EnrolmentMO;
import com.mercadona.alejandro.dev.driven.repositories.models.StudentMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

// @Configuration
@Slf4j
public class Runner implements CommandLineRunner {

  @Autowired
  private StudentMOJpaRepository studentMOJpaRepository;

  @Override
  public void run(String... args) throws Exception {

    StudentMO student = StudentMO.builder()
            .name("Alejandro")
            .build();

    CourseMO course = CourseMO.builder()
      .name("Matemáticas")
      .build();

    EnrolmentMO enrolmentMO = EnrolmentMO.builder()
      .createdAt(LocalDateTime.now())
      .student(student)
      .course(course)
      .build();

    student.addEnrolment(enrolmentMO, course);


    studentMOJpaRepository.save(student);

  }
}
