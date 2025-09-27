package com.mercadona.alejandro.dev.driven.repositories.models;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class StudentMO {

  @Id
  @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
  private Set<EnrolmentMO> enrolments;

  public void addEnrolment(EnrolmentMO enrolment, CourseMO course) {

    if(this.enrolments == null)
      this.enrolments = new HashSet<>();

    enrolments.add(enrolment);
    enrolment.setStudent(this);

    enrolment.setId(EnrolmentPk.builder()
            .studentId(this.id)
            .courseId(course.getId())
            .build());
  }
}
