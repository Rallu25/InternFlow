package com.ibm.internflow.repository;

import com.ibm.internflow.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface StudentRepository extends JpaRepository<StudentEntity, Long>{
    List<StudentEntity> findByTeam_TeamId(Long teamId);
}
