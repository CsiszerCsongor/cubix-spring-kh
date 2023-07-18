package com.sb_kh.week1.controller;

import com.sb_kh.week1.dto.CreateTeacherDTO;
import com.sb_kh.week1.dto.HistoryData;
import com.sb_kh.week1.dto.TeacherDTO;
import com.sb_kh.week1.dto.TeacherResponseDTO;
import com.sb_kh.week1.dto.UpdateTeacherDTO;
import com.sb_kh.week1.service.TeacherService;
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

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/{id}")
    public TeacherResponseDTO getTeacherInformationWrong(
            @PathVariable
            Long id
    ) {
        return teacherService.getTeacherInformation(id);
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<HistoryData<TeacherResponseDTO>>> getTeacherHistory(
            @PathVariable
            Long id
    ) {
        return ResponseEntity.ok(teacherService.getTeacherHistory(id));
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDTO> createTeacher(
            @RequestBody
            @Valid
            final CreateTeacherDTO dto
    ) {
        return ResponseEntity.ok(teacherService.createTeacher(dto));
    }

    @PutMapping
    public ResponseEntity<TeacherResponseDTO> updateTeacher(
            @RequestBody
            @Valid
            final UpdateTeacherDTO dto
    ) {
        return ResponseEntity.ok(teacherService.updateTeacher(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTeacher(
            @PathVariable
            final Long id
    ) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok().build();
    }


}
