package com.ibm.internflow.entity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Mentor")
public class MentorEntity {
    @Id
    @SequenceGenerator(
            name = "mentorGenerator",
            sequenceName = "intern_flow.sq_mentor_id",
            allocationSize = 1
    )

    @Column(name = "mentor_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentorGenerator")
    private Long mentorId;

    @Column(name = "first_name")
    @Nonnull
    private String firstName;

    @Column(name = "last_name")
    @Nonnull
    private String lastName;

    @Column(name = "email")
    @Nonnull
    private String email;

    @OneToMany(mappedBy = "grades")
    private Set<GradesEntity> grades;

    @OneToMany(mappedBy = "attendance")
    private Set<AttendanceEntity> attendances;

    public MentorEntity() {
        // no-arg constructor
    }

    public Long getMentorId() {
        return mentorId;
    }

    public void setMentorId(Long mentorId) {
        this.mentorId = mentorId;
    }

    @Nonnull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Nonnull String firstName) {
        this.firstName = firstName;
    }

    @Nonnull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@Nonnull String lastName) {
        this.lastName = lastName;
    }

    @Nonnull
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nonnull String email) {
        this.email = email;
    }

    public Set<GradesEntity> getGrades() {
        return grades;
    }

    public void setGrades(Set<GradesEntity> grades) {
        this.grades = grades;
    }

    public Set<AttendanceEntity> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<AttendanceEntity> attendances) {
        this.attendances = attendances;
    }
}
