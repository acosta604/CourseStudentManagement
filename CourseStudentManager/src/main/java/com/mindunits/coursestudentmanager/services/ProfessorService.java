package com.mindunits.coursestudentmanager.services;

import com.mindunits.coursestudentmanager.models.Professor;
import com.mindunits.coursestudentmanager.repository.ProfessorRepository;
import com.mindunits.coursestudentmanager.repository.ProfessorRepositoryPost;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    private final ProfessorRepositoryPost professorRepositoryPost;

    public ProfessorService(ProfessorRepositoryPost professorRepositoryPost) {
        this.professorRepositoryPost = professorRepositoryPost;
    }

    public Professor saveProfessor(Professor professor){
        return professorRepositoryPost.save(professor);
    }
}
