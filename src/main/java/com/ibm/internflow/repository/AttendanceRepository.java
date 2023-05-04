package com.ibm.internflow.repository;

import com.ibm.internflow.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository <AttendanceEntity, Long>{
}
