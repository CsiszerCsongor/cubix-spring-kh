package com.sb_kh.week1.service;

import com.sb_kh.week1.model.Course;
import com.sb_kh.week1.model.Student;
import com.sb_kh.week1.model.Teacher;
import com.sb_kh.week1.repository.CourseRepository;
import com.sb_kh.week1.repository.StudentRepository;
import com.sb_kh.week1.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class InitDbService {

	private final CourseRepository courseRepository;
	private final TeacherRepository teacherRepository;
	private final StudentRepository studentRepository;
	private final JdbcTemplate jdbcTemplate;

	@Transactional
	public void deletePreviousData() {
		courseRepository.deleteAll();
		teacherRepository.deleteAll();
		studentRepository.deleteAll();
	}

	@Transactional
	public void deleteAudTables() {
		jdbcTemplate.update("DELETE from course_aud");
		jdbcTemplate.update("DELETE from teacher_aud");
		jdbcTemplate.update("DELETE from student_aud");
		jdbcTemplate.update("DELETE from student_curse_aud");
		jdbcTemplate.update("DELETE from teacher_course_aud ");
	}

	@Transactional
	public void addInitData() {
		Course course1 = courseRepository.save(new Course("Computer Science"));
		Course course2 = courseRepository.save(new Course("Law"));
		Course course3 = courseRepository.save(new Course("Criminology"));

		Set<Course> teacher1Courses = Set.of(course1);
		Set<Course> teacher2Courses = Set.of(course2);
		Set<Course> teacher3Courses = Set.of(course2, course3);

		Teacher teacher1 = teacherRepository.save(new Teacher("Kis Lajos", LocalDateTime.of(1987, Month.APRIL, 15,0,0,0), teacher1Courses));
		Teacher teacher2 = teacherRepository.save(new Teacher("Eszterházy János", LocalDateTime.of(1966, Month.JANUARY, 22,0,0,0), teacher2Courses));
		Teacher teacher3 = teacherRepository.save(new Teacher("Sípos Veronika", LocalDateTime.of(1990, Month.FEBRUARY, 10,0,0,0), teacher3Courses));

		Set<Course> student1Courses = Set.of(course1, course2);
		Set<Course> student2Courses = Set.of(course1, course3);
		Set<Course> student3Courses = Set.of(course1, course2, course3);
		Set<Course> student4Courses = Set.of(course2, course3);

		Student student1 = studentRepository.save(new Student(1, "Nagy Márta", LocalDateTime.of(1999, Month.FEBRUARY, 21,0,0,0), student1Courses, 1));
		Student student2 = studentRepository.save(new Student(2, "Fenyves Csongor", LocalDateTime.of(1998, Month.DECEMBER, 4,0,0,0), student2Courses, 3));
		Student student3 = studentRepository.save(new Student(3, "Tulit Ibolya", LocalDateTime.of(2000, Month.AUGUST, 6,0,0,0), student3Courses, 2));
		Student student4 = studentRepository.save(new Student(2, "Kapás Emese", LocalDateTime.of(1998, Month.SEPTEMBER, 19,0,0,0), student4Courses, 4));

//		Set<Student> computerScienceStudents = Set.of(student1, student2, student3);
//		Set<Teacher> computerScienceTeachers = Set.of(teacher1);
//		Set<Student> lawStudents = Set.of(student1, student3, student4);
//		Set<Teacher> lawTeachers = Set.of(teacher2, teacher3);
//		Set<Student> criminologyStudents = Set.of(student2, student3, student4);
//		Set<Teacher> criminologyTeachers = Set.of(teacher3);
//
//		courseRepository.save(new Course("Computer Science", computerScienceStudents, computerScienceTeachers));
//		courseRepository.save(new Course("Law", lawStudents, lawTeachers));
//		courseRepository.save(new Course("Criminology", criminologyStudents, criminologyTeachers));
	}
}
