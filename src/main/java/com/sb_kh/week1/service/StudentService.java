package com.sb_kh.week1.service;

import com.sb_kh.week1.dto.CreateStudentDTO;
import com.sb_kh.week1.dto.StudentCentralDataDTO;
import com.sb_kh.week1.dto.StudentResponseDTO;
import com.sb_kh.week1.dto.UpdateStudentDTO;
import com.sb_kh.week1.exception.StudentNotFoundException;
import com.sb_kh.week1.mapper.StudentMapper;
import com.sb_kh.week1.model.Student;
import com.sb_kh.week1.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final MockedService mockedService;

    public StudentResponseDTO getStudentInformation(final Long id) {
        Student student =
                studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found!"));

        return studentMapper.studentToDTO(student);
    }

    //    @Scheduled(cron = "${scheduler.student.updater}")
    public void updateStudents() throws InterruptedException {
        System.out.println("Update student scheduler started...");
        final List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            final StudentCentralDataDTO response = mockedService.freelyUsedSemesters(student.getStudentCentralId());
            student.setStudentCentralId(response.getCentralId());
            student.setFreelyUsedSemester(response.getUsedFreeSemestersNumber());
            studentRepository.save(student);
            System.out.println("Student updated.");
        }

        System.out.println("Update student scheduler stopped...");
    }

    public StudentResponseDTO createStudent(final CreateStudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setBirthDate(dto.getBirthDate());
        student.setSemester(dto.getSemester());
        student.setStudentCentralId(dto.getStudentCentralId());

        return studentMapper.studentToDTO(studentRepository.save(student));
    }

    @Transactional
    public StudentResponseDTO updateStudent(final UpdateStudentDTO dto) {
        Student student = studentRepository.findById(dto.getId())
                                           .orElseThrow(() -> new StudentNotFoundException("Student not found!"));
        student.setName(dto.getName());
        student.setBirthDate(dto.getBirthDate());
        student.setSemester(dto.getSemester());
        student.setStudentCentralId(dto.getStudentCentralId());

        return studentMapper.studentToDTO(student);
    }

    public void deleteStudent(final Long id) {
        studentRepository.deleteById(id);
    }

}
