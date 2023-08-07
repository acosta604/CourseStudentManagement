package com.mindunits.coursestudentmanager.controllers;

import com.mindunits.coursestudentmanager.models.Professor;
import com.mindunits.coursestudentmanager.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping("/api/professor")
    public ResponseEntity<Professor> saveProfessor(@RequestBody Professor professor){
        try {
            Professor newProfessor = professorService.saveProfessor(professor);
            return new ResponseEntity<>(newProfessor, HttpStatus.CREATED);
        } catch (NoSuchElementException e){
            return  ResponseEntity.notFound().build();
        }
    }


}
