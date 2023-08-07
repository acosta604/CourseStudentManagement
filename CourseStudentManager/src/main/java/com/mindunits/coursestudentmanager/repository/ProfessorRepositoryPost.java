package com.mindunits.coursestudentmanager.repository;

import com.mindunits.coursestudentmanager.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepositoryPost extends JpaRepository<Professor, Long> {
}
