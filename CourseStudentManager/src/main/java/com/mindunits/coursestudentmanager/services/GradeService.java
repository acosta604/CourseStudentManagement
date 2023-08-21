package com.mindunits.coursestudentmanager.services;

import com.mindunits.coursestudentmanager.models.Grade;
import com.mindunits.coursestudentmanager.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GradeService {

  private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public void deleteGradeById(Long id) {
        gradeRepository.deleteById(id);
    }

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public Grade getGradeById(Long id) {
        return gradeRepository.getById(id);
    }

    public Grade updateGrade(Long id, Double grade, String description) {
        Grade existingGrade = gradeRepository.findById(id).orElse(null);

        if (existingGrade == null) {
            throw new NoSuchElementException("Grade with ID " + id + " not found");
        }

        existingGrade.setGrade(grade != null ? grade : existingGrade.getGrade());
        existingGrade.setDescription(description != null ? description : existingGrade.getDescription());

        return gradeRepository.save(existingGrade);
    }

}


