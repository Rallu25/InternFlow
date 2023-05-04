package com.ibm.internflow.repository;

import com.ibm.internflow.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TeamRepository extends JpaRepository<TeamEntity, Long>{
}
