package com.sb_kh.week1.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.sb_kh.week1.dto.CourseDTO;
import com.sb_kh.week1.dto.CreateCourseDTO;
import com.sb_kh.week1.dto.HistoryData;
import com.sb_kh.week1.dto.RequestDTO;
import com.sb_kh.week1.dto.StudentDTO;
import com.sb_kh.week1.dto.TeacherResponseDTO;
import com.sb_kh.week1.dto.UpdateCourseDTO;
import com.sb_kh.week1.exception.CourseNotFoundException;
import com.sb_kh.week1.exception.StudentNotFoundException;
import com.sb_kh.week1.mapper.CourseMapper;
import com.sb_kh.week1.model.Course;
import com.sb_kh.week1.model.QCourse;
import com.sb_kh.week1.model.Student;
import com.sb_kh.week1.model.Teacher;
import com.sb_kh.week1.repository.CourseRepository;
import com.sb_kh.week1.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;
    @PersistenceContext
    private final EntityManager entityManager;
    private final StudentRepository studentRepository;

    public List<Course> findById(final RequestDTO dto) {

        List<Predicate> predicates = new ArrayList<>();

        QCourse course = QCourse.course;

        if (dto.getId() != null) {
            predicates.add(course.id.eq(dto.getId()));
        }

        if (StringUtils.hasText(dto.getName())) {
            predicates.add(course.name.startsWithIgnoreCase(dto.getName()));
        }

        if (StringUtils.hasText(dto.getTeacherDTO().getName())) {
            predicates.add(course.teachers.any().name.startsWithIgnoreCase(dto.getTeacherDTO().getName()));
        }

        if (dto.getStudentId() != null) {
            predicates.add(course.students.any().id.eq(dto.getId()));
        }

        return Lists.newArrayList(courseRepository.findAll(ExpressionUtils.allOf(predicates)));
    }

    @Cacheable("findCoursesWithPredicate")
    public List<CourseDTO> findCourses(final boolean isFull, final Predicate predicate, final Pageable pageable) {
        List<Course> courses =
                isFull ? findAllWithStudentsAndTeachers(predicate, pageable) : findAll(predicate, pageable);

        return isFull ? courseMapper.coursesToDTOs(courses) : courseMapper.courseSummariesToDTOs(courses);
    }

    public List<Course> findAll(final Predicate predicate, final Pageable pageable) {
        return Lists.newArrayList(courseRepository.findAll(predicate, pageable));
    }

    public List<Course> findAllWithStudentsAndTeachers(final Predicate predicate, final Pageable pageable) {
        return Lists.newArrayList(courseRepository.findAllWithStudentsAndTeachers(pageable));
    }

    public CourseDTO getCourseInformation(final Long id) {
        Course course =
                courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course not found!"));

        return courseMapper.courseToDTO(course);
    }


    public CourseDTO createCourse(final CreateCourseDTO dto) {
        Course course = new Course();
        course.setName(dto.getName());

        return courseMapper.courseToDTO(courseRepository.save(course));
    }

    @Transactional
    public CourseDTO updateCourse(final UpdateCourseDTO dto, final Long courseId) {
        Course course = courseRepository.findById(courseId)
                                        .orElseThrow(() -> new StudentNotFoundException("Student not found!"));
        course.setName(dto.getName());

        if (!dto.getStudentIds().isEmpty()) {
            List<Student> students = studentRepository.findAllById(dto.getStudentIds());
            course.setStudents(Sets.newHashSet(students));

            for(Student student : students) {
                student.getCourses().add(course);
                studentRepository.save(student);
            }
        }

        return courseMapper.courseToDTO(courseRepository.save(course));
    }

    public void deleteCourse(final Long id) {
        courseRepository.deleteById(id);
    }

    @Transactional
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<HistoryData<CourseDTO>> getCourseHistory(final Long id) {
        return AuditReaderFactory.get(entityManager)
                                 .createQuery()
                                 .forRevisionsOfEntity(Course.class, false, true)
                                 .add(AuditEntity.property("id").eq(id))
                                 .getResultList()
                                 .stream()
                                 .map(o -> {
                                     Object[] objArray = (Object[]) o;
                                     Course course = (Course) objArray[0];

                                     DefaultRevisionEntity revisionEntity = (DefaultRevisionEntity) objArray[1];
                                     return new HistoryData<CourseDTO>(courseMapper.courseToDTO((Course) objArray[0]),
                                                                                revisionEntity.getId(),
                                                                                (RevisionType) objArray[2],
                                                                                revisionEntity.getRevisionDate());
                                 })
                                 .toList();
    }
}
