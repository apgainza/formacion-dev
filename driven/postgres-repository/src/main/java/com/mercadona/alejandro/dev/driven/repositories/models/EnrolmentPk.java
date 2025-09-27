package com.mercadona.alejandro.dev.driven.repositories.models;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnrolmentPk implements Serializable {

  @Column(name = "student_id")
  private Long studentId;
  @Column(name = "course_id")
  private Long courseId;
}
