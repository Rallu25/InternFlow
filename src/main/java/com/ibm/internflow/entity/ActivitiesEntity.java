package com.ibm.internflow.entity;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Activities")
public class ActivitiesEntity {
    @Id
    @SequenceGenerator(
            name = "activitiesGenerator",
            sequenceName = "intern_flow.sq_activity_id",
            allocationSize = 1
    )
    @Column(name = "activity_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activitiesGenerator")
    private Long activityId;

    @Column(name = "activity_name")
    @NonNull
    private String activityName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_activities",
            joinColumns = {@JoinColumn(name = "activity_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private Set<StudentEntity> students = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private AttendanceEntity attendance;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id")
    private GradesEntity grade;

    public ActivitiesEntity() {
        //no-arg Constructor
    }

    //Getters and Setters
    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Set<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentEntity> students) {
        this.students = students;
    }

    public GradesEntity getGrade() {
        return grade;
    }

    public void setGrade(GradesEntity grade) {
        this.grade = grade;
    }

    public AttendanceEntity getAttendance() {
        return attendance;
    }

    public void setAttendance(AttendanceEntity attendance) {
        this.attendance = attendance;
    }
}
