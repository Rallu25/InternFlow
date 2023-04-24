package com.ibm.internflow.entity;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

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
}
