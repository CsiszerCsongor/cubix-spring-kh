package com.sb_kh.week1.mapper;


import com.sb_kh.week1.dto.CourseDTO;
import com.sb_kh.week1.dto.TeacherDTO;
import com.sb_kh.week1.model.Course;
import com.sb_kh.week1.model.Teacher;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    List<CourseDTO> coursesToDTOs(List<Course> courses);

    @Mapping(target = "teachers", source = "teachers")
    CourseDTO courseToDTO(Course course);

    Set<TeacherDTO> map(Set<Teacher> teachers);

    TeacherDTO teacherToDTO(Teacher teacher);

    @IterableMapping(qualifiedByName = "summary")
    List<CourseDTO> courseSummariesToDTOs(List<Course> courses);

    @Named("summary")
    @Mapping(target = "teachers", ignore = true)
    @Mapping(target = "students", ignore = true)
    CourseDTO courseSummaryToDTO(Course course);

    Course dtoToCourse(CourseDTO courseDTO);
}
