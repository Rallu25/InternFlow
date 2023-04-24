package com.ibm.internflow.entity;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

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
}
