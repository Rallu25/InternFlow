package com.ibm.internflow.repository;

import com.ibm.internflow.entity.ActivitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.function.LongBinaryOperator;

@Repository
public interface ActivitiesRepository extends JpaRepository<ActivitiesEntity, Long>{
}
