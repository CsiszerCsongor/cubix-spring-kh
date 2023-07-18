package com.sb_kh.week1.mapper;


import com.sb_kh.week1.dto.CourseDTO;
import com.sb_kh.week1.dto.StudentResponseDTO;
import com.sb_kh.week1.dto.TeacherDTO;
import com.sb_kh.week1.dto.TeacherResponseDTO;
import com.sb_kh.week1.model.Course;
import com.sb_kh.week1.model.Student;
import com.sb_kh.week1.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    List<TeacherResponseDTO> teachersToDTOs(List<Teacher> teachers);

    TeacherResponseDTO teacherToDTO(Teacher teacher);

    @Mapping(target = "students", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    CourseDTO courseToDTO(Course course);

    StudentResponseDTO studentToDTO(Student student);

    Teacher dtoToTeacher(TeacherResponseDTO dto);

}
