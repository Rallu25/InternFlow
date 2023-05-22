package com.ibm.internflow.dto;

public class AttendanceDto {
    private Long attendanceId;
    private String status;

    public AttendanceDto() {
    }

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
