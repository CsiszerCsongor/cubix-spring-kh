package com.sb_kh.week1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private Long id;
    private String name;
    private Set<TeacherDTO> teachers;
    private Set<StudentDTO> students;

}
