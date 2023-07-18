package com.sb_kh.week1.repository;

import com.sb_kh.week1.model.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @EntityGraph(attributePaths = {"courses"})
    @Query("select s from Student s where s.id = :id")
    Optional<Student> findById(@Param("id") final Long id);

}
