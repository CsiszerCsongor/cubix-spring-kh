package com.sb_kh.week1.service;

import com.sb_kh.week1.dto.CreateTeacherDTO;
import com.sb_kh.week1.dto.HistoryData;
import com.sb_kh.week1.dto.TeacherResponseDTO;
import com.sb_kh.week1.dto.UpdateTeacherDTO;
import com.sb_kh.week1.exception.StudentNotFoundException;
import com.sb_kh.week1.exception.TeacherNotFoundException;
import com.sb_kh.week1.mapper.TeacherMapper;
import com.sb_kh.week1.model.Teacher;
import com.sb_kh.week1.repository.TeacherRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @PersistenceContext
    private final EntityManager entityManager;

    public TeacherResponseDTO getTeacherInformation(final Long id) {
        Teacher teacher =
                teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException("Teacher not found!"));

        return teacherMapper.teacherToDTO(teacher);
    }

    public TeacherResponseDTO createTeacher(final CreateTeacherDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setName(dto.getName());
        teacher.setBirthDate(dto.getBirthDate());

        return teacherMapper.teacherToDTO(teacherRepository.save(teacher));
    }

    @Transactional
    public TeacherResponseDTO updateTeacher(final UpdateTeacherDTO dto) {
        Teacher teacher = teacherRepository.findById(dto.getId())
                                           .orElseThrow(() -> new StudentNotFoundException("Student not found!"));
        teacher.setName(dto.getName());
        teacher.setBirthDate(dto.getBirthDate());

        return teacherMapper.teacherToDTO(teacher);
    }

    public void deleteTeacher(final Long id) {
        teacherRepository.deleteById(id);
    }

    @Transactional
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<HistoryData<TeacherResponseDTO>> getTeacherHistory(final Long id) {
        return AuditReaderFactory.get(entityManager)
                                 .createQuery()
                                 .forRevisionsOfEntity(Teacher.class, false, true)
                                 .add(AuditEntity.property("id").eq(id))
                                 .getResultList()
                                 .stream()
                                 .map(o -> {
                                     Object[] objArray = (Object[]) o;
                                     DefaultRevisionEntity revisionEntity = (DefaultRevisionEntity) objArray[1];
                                     return new HistoryData<TeacherResponseDTO>(teacherMapper.teacherToDTO((Teacher) objArray[0]),
                                                                                revisionEntity.getId(),
                                                                                (RevisionType) objArray[2],
                                                                                revisionEntity.getRevisionDate());
                                 })
                                 .toList();
    }

}
