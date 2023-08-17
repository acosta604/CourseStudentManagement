package com.mindunits.coursestudentmanager.controllers;

import com.mindunits.coursestudentmanager.models.Professor;
import com.mindunits.coursestudentmanager.models.Student;
import com.mindunits.coursestudentmanager.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/api/professor")
    public ResponseEntity<List<Professor>> getAllProfessor(){
        try {
            List<Professor> getAllProfessor = professorService.getAllProfessor();
            return ResponseEntity.ok(getAllProfessor);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/professor/{id}")
    public ResponseEntity<Professor> getIdProfessor(@PathVariable Long id) {
        try {
            Professor getIdProfessor = professorService.getIdProfessor(id);
            return ResponseEntity.ok(getIdProfessor);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    // como profesor esta relacionado con la tabla curso, se creo una columna nueva
    //para que al enviar la solucitud delete, cambie el estado del profesor de activo a inactivo
    //De esta manera sabremos el estado del profesor y no afectara el funcionamiento del programa
    @DeleteMapping("/api/professor/{id}")
    public ResponseEntity<String> deleteProfessor(@PathVariable Long id) {
        try {
            professorService.desactivateProfessor(id);
            return ResponseEntity.ok("Professor desactivated successfully.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    /*
    @DeleteMapping("/api/professor/{id}")
    public ResponseEntity<Professor> deleteProfessor(@PathVariable Long id) {
        // No puedo eliminar un profesor que esta siendo utilizado en un curso
        // ver que hacer en ese caso, con herencia en clases y la configuracion en los entities

        try {
            professorService.deleteProfessorById(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    } */

    @PutMapping("/api/professor/update/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @RequestBody Professor professor) {
        try {
            Professor updateProfessor = professorService.updateProfessor(
                    id,
                    professor.getName(),
                    professor.getEmail(),
                    professor.getPhone(),
                    professor.isActive()
                    );
            return ResponseEntity.ok(updateProfessor);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
