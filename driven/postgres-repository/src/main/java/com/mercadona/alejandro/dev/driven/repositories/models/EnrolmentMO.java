package com.mercadona.alejandro.dev.driven.repositories.models;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enrolment")
public class EnrolmentMO {

  @EmbeddedId
  private EnrolmentPk id;

  @MapsId("studentId")
  @ManyToOne
  @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "enrolment_student_id_fk"))
  private StudentMO student;

  @MapsId("courseId")
  @ManyToOne
  @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "enrolment_course_id_fk"))
  private CourseMO course;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;
}
