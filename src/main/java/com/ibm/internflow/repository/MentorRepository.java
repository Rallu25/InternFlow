package com.ibm.internflow.repository;

import com.ibm.internflow.entity.MentorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MentorRepository extends JpaRepository<MentorEntity, Long> {
}
