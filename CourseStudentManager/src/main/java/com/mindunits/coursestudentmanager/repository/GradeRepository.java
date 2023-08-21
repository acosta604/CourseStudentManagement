package com.mindunits.coursestudentmanager.repository;

import com.mindunits.coursestudentmanager.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
