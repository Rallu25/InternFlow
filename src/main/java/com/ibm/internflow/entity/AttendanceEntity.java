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

    @OneToOne(mappedBy = "attendance")
    private ActivitiesEntity activities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private MentorEntity mentor;


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

    public ActivitiesEntity getActivities() {
        return activities;
    }

    public void setActivities(ActivitiesEntity activities) {
        this.activities = activities;
    }

    public MentorEntity getMentor() {
        return mentor;
    }

    public void setMentor(MentorEntity mentor) {
        this.mentor = mentor;
    }
}
