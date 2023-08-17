package com.mindunits.coursestudentmanager.services;
import com.mindunits.coursestudentmanager.models.Professor;
import com.mindunits.coursestudentmanager.repository.ProfessorRepositoryPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProfessorService {

    private final ProfessorRepositoryPost professorRepositoryPost;

    @Autowired
    public ProfessorService(ProfessorRepositoryPost professorRepositoryPost) {
        this.professorRepositoryPost = professorRepositoryPost;
    }

    public Professor saveProfessor(Professor professor) {
        return professorRepositoryPost.save(professor);
    }

    public List<Professor> getAllProfessor() {
        return professorRepositoryPost.findAll();
    }

    public Professor getIdProfessor(Long id) {
        return professorRepositoryPost.getById(id);
    }

    public void deleteProfessorById(Long id) {
        professorRepositoryPost.deleteById(id);
    }

    public void desactivateProfessor(Long id) {
        Professor professor = professorRepositoryPost.findById(id)
                .orElseThrow(NoSuchElementException::new);

        professor.setActive(false);
        professorRepositoryPost.save(professor);
    }

    public Professor updateProfessor(Long id, String nameProfessor, String emailProfessor, String phoneProfessor, Boolean active) {
        Professor existingProfessor = professorRepositoryPost.findById(id).orElse(null);

        if (existingProfessor == null) {
            throw new NoSuchElementException(String.format("Professor with id %s not found.", id));
        }

        existingProfessor.setName(nameProfessor == null ? existingProfessor.getName() : nameProfessor);
        existingProfessor.setEmail(emailProfessor == null ? existingProfessor.getEmail() : emailProfessor);
        existingProfessor.setPhone(phoneProfessor == null ? existingProfessor.getPhone() : phoneProfessor);

        if (active != null) {
            existingProfessor.setActive(active);
        }

        return professorRepositoryPost.save(existingProfessor);
    }
}