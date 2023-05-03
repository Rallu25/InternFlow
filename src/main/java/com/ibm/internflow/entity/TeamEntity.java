package com.ibm.internflow.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Team")
public class TeamEntity {
    @Id
    @SequenceGenerator(
            name = "teamGenerator",
            sequenceName = "intern_flow.sq_team_id",
            allocationSize = 1
    )
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamGenerator")
    private Long teamId;

    @OneToMany(mappedBy = "student")
    private Set<StudentEntity> students;


    public TeamEntity() {
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Set<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentEntity> students) {
        this.students = students;
    }
}
