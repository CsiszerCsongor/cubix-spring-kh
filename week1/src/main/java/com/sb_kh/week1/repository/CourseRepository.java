package com.sb_kh.week1.repository;

import com.sb_kh.week1.model.Course;
import com.sb_kh.week1.model.QCourse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.List;
import java.util.Optional;

public interface CourseRepository
        extends JpaRepository<Course, Long>, QuerydslPredicateExecutor<Course>, QuerydslBinderCustomizer<QCourse> {

    @EntityGraph(attributePaths = {"teachers", "students"})
    @Query("select c from Course c")
    List<Course> findAllWithStudentsAndTeachers(Pageable pageable);

    @Override
    default void customize(QuerydslBindings bindings, QCourse course) {
        bindings.bind(course.name).first((path, value) -> path.startsWithIgnoreCase(value));
        bindings.bind(course.teachers.any().name).first((path, value) -> path.startsWithIgnoreCase(value));
        bindings.bind(course.students.any().id).first((path, value) -> path.eq(value));

        bindings.bind(course.students.any().semester).all(((path, values) ->{
            if (values.size() != 2) return Optional.empty();
            int semesterFrom = values.iterator().next();
            int semesterTo = values.iterator().next();

            return Optional.of(path.between(semesterFrom, semesterTo));
        }));
    }

}
