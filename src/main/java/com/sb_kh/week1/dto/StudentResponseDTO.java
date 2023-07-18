package com.sb_kh.week1.dto;

import com.sb_kh.week1.model.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {
    private Long id;
    private String name;
    private LocalDateTime birthDate;
    private Integer semester;

    private Set<CourseDTO> courses;
}
