package com.ibm.internflow.repository;

import com.ibm.internflow.entity.GradesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradesRepository extends JpaRepository<GradesEntity, Long> {
}
