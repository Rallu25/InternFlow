package com.ibm.internflow.repository;

import com.ibm.internflow.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StudentRepository extends JpaRepository<StudentEntity, Long>{
}
