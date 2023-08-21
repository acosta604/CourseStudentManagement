package com.mindunits.coursestudentmanager.repository;

import com.mindunits.coursestudentmanager.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProfessorRepositoryPost extends JpaRepository<Professor, Long> {
    List<Professor> findByActiveTrue();


}
