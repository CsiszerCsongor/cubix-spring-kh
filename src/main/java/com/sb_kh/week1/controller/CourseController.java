package com.sb_kh.week1.controller;

import com.querydsl.core.types.Predicate;
import com.sb_kh.week1.dto.CourseDTO;
import com.sb_kh.week1.dto.CreateCourseDTO;
import com.sb_kh.week1.dto.HistoryData;
import com.sb_kh.week1.dto.RequestDTO;
import com.sb_kh.week1.dto.TeacherResponseDTO;
import com.sb_kh.week1.dto.UpdateCourseDTO;
import com.sb_kh.week1.mapper.CourseMapper;
import com.sb_kh.week1.model.Course;
import com.sb_kh.week1.model.Course_;
import com.sb_kh.week1.service.CourseService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping("/search")
    public List<Course> findCourse(
            @RequestBody
            RequestDTO requestDTO
    ) {
        return courseService.findById(requestDTO);
    }

    @GetMapping
    public List<CourseDTO> findCourseQuerydsl(
            @RequestParam("full")
            Optional<Boolean> full,
            @QuerydslPredicate(root = Course.class)
            Predicate predicate,
            @SortDefault(value = Course_.ID, direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        boolean isFull = full.orElse(false);

        return courseService.findCourses(isFull, predicate, pageable);
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<HistoryData<CourseDTO>>> getCourseHistory(
            @PathVariable
            Long id
    ) {
        return ResponseEntity.ok(courseService.getCourseHistory(id));
    }

    @GetMapping("/{id}")
    public CourseDTO getStudentInformationWrong(
            @PathVariable
            Long id
    ) {
        return courseService.getCourseInformation(id);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(
            @RequestBody @Valid
            final CreateCourseDTO dto
    ) {
        return ResponseEntity.ok(courseService.createCourse(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(
            @RequestBody @Valid
            final UpdateCourseDTO dto,
            @PathVariable("id") Long courseId
    ) {
        return ResponseEntity.ok(courseService.updateCourse(dto, courseId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCourse(
            @PathVariable
            final Long id
    ) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }

}
