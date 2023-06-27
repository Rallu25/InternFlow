package com.ibm.internflow.entity;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.Set;

@Entity
@Table(name = "Attendance")

public class AttendanceEntity {
    @Id
    @SequenceGenerator(
            name = "attendanceGenerator",
            sequenceName = "intern_flow.sq_attendance_id",
            allocationSize = 1
    )

    @Column(name = "attendance_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendanceGenerator")
    private Long attendanceId;

    @Column(name = "status")
    @NonNull
    private String status;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private MentorEntity mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private ActivitiesEntity activity;

    public AttendanceEntity(){
        // no-arg constructor
    }

    // Getters and Setters
    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    @NonNull
    public String getStatus() {
        return status;
    }

    public void setStatus(@NonNull String status) {
        this.status = status;
    }


    public MentorEntity getMentor() {
        return mentor;
    }

    public void setMentor(MentorEntity mentor) {
        this.mentor = mentor;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public ActivitiesEntity getActivity() {
        return activity;
    }

    public void setActivity(ActivitiesEntity activity) {
        this.activity = activity;
    }
}
