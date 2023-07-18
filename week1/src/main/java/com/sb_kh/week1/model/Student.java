package com.sb_kh.week1.model;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Cacheable
@Audited
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    @EqualsAndHashCode.Include()
    private Long id;

    private String name;

    @Column(name = "birthdate")
    private LocalDateTime birthDate;
    private int semester;

    @ManyToMany
    @JoinTable(
            name = "student_curse",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
    )
    private Set<Course> courses;

    @Column(name = "student_central_id")
    private int studentCentralId;
    @Column(name = "freely_used_semesters")
    private int freelyUsedSemester;

    public Student(final int semester, final String name, final LocalDateTime birthDate) {
        this.semester = semester;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Student(final int semester, final String name, final LocalDateTime birthDate, final Set<Course> courses,
                   final int studentCentralId) {
        this.semester = semester;
        this.name = name;
        this.birthDate = birthDate;
        this.courses = courses;
        this.studentCentralId = studentCentralId;
    }
}
