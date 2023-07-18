package com.sb_kh.week1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {

    private Long id;
    private String name;
    private TeacherDTO teacherDTO;
    private Long studentId;
    private Integer semesterNumber;

}
