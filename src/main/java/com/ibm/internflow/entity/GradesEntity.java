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

    @OneToOne(mappedBy = "activities")
    private ActivitiesEntity activities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private MentorEntity mentor;

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