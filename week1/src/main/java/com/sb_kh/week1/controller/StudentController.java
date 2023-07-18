package com.sb_kh.week1.controller;

import com.sb_kh.week1.dto.CreateStudentDTO;
import com.sb_kh.week1.dto.StudentResponseDTO;
import com.sb_kh.week1.dto.UpdateStudentDTO;
import com.sb_kh.week1.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{id}")
    public StudentResponseDTO getStudentInformationWrong(
            @PathVariable
            Long id
    ) {
        return studentService.getStudentInformation(id);
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(
            @RequestBody @Valid
            final CreateStudentDTO dto
    ) {
        return ResponseEntity.ok(studentService.createStudent(dto));
    }

    @PutMapping
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @RequestBody @Valid
            final UpdateStudentDTO dto
    ) {
        return ResponseEntity.ok(studentService.updateStudent(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteStudent(
            @PathVariable
            final Long id
    ) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

}
