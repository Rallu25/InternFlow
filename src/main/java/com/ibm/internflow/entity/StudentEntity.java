package com.ibm.internflow.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Student")
public class StudentEntity {

    @Id
    @SequenceGenerator(
            name = "studentGenerator",
            sequenceName = "intern_flow.sq_student_id",
            allocationSize = 1
    )
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentGenerator")
    private Long studentId;


    @Column(name = "first_name")
    @Nonnull
    private String firstName;

    @Column(name = "last_name")
    @Nonnull
    private String lastName;

    @Column(name = "email")
    @Nonnull
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private StudentEntity student;

    @ManyToMany(mappedBy = "students")
    private Set<ActivitiesEntity> activities = new HashSet<>();



    public Long getStudentId() {
        return studentId;
    }

    public StudentEntity() {

    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }
}
