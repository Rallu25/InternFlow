package com.ibm.internflow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
}