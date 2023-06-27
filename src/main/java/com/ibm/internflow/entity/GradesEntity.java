package com.ibm.internflow.entity;

import jakarta.persistence.*;
import jakarta.annotation.Nonnull;
@Entity
@Table(name = "Grades")
public class GradesEntity {
    @Id
    @SequenceGenerator(
            name = "gradesGenerator",
            sequenceName = "intern_flow.sq_grade_id",
            allocationSize = 1
    )
    @Column(name = "grade_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gradesGenerator")
    private Long gradeId;

    @Column(name = "grade_value")
    @Nonnull
    private int gradeValue;

    @Column(name="comment")
    private String comment;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private MentorEntity mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private ActivitiesEntity activity;

    public GradesEntity() {
        //no-arg Constructor
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public int getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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