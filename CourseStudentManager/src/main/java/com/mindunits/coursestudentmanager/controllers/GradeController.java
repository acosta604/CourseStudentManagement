package com.mindunits.coursestudentmanager.controllers;


import com.mindunits.coursestudentmanager.models.Grade;
import com.mindunits.coursestudentmanager.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*") //para cargar los datos de la bd en el front
@RestController
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @PostMapping("/api/grade")
    public ResponseEntity<Grade> saveGrade(@RequestBody Grade grade) {
        try{
            Grade save = gradeService.saveGrade(grade);
            return ResponseEntity.ok(save);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/grade/{id}")
    public ResponseEntity<Grade> deleteGrade(@PathVariable Long id) {
        try {
            gradeService.deleteGradeById(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/grades")
    public ResponseEntity<List<Grade>> getAllGrades() {
        try {
            List<Grade> getAll = gradeService.getAllGrades();
            return ResponseEntity.ok(getAll);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/api/grade/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable Long id) {
        try {
            Grade grade = gradeService.getGradeById(id);
            return ResponseEntity.ok(grade);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/grade/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @RequestBody Grade updatedGrade) {
        try {
            Grade updateGrade = gradeService.updateGrade(
                    id,
                    updatedGrade.getGrade(),
                    updatedGrade.getDescription());
            return ResponseEntity.ok(updateGrade);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }



}
