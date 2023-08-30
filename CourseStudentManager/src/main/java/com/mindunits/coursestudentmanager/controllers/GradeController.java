package com.mindunits.coursestudentmanager.controllers;


import com.mindunits.coursestudentmanager.models.Grade;
import com.mindunits.coursestudentmanager.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("/api/grade/student/{studentId}")
    public Map<String, Object> getGradesByStudent(@PathVariable Long studentId) {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Grade> grades = gradeService.getGradesByStudent(studentId);

            if (!grades.isEmpty()) {
                response.put("studentId", grades.get(0).getStudent().getId());
                response.put("studentName", grades.get(0).getStudent().getName());

                List<Map<String, Object>> courseGrades = new ArrayList<>();
                for (Grade grade : grades) {
                    Map<String, Object> courseGrade = new HashMap<>();
                    courseGrade.put("courseName", grade.getCourse().getName());
                    courseGrade.put("idGrade", grade.getId());
                    courseGrade.put("grade", grade.getGrade());
                    courseGrade.put("gradeDescription", grade.getDescription()); // Agregar descripción de la nota
                    courseGrades.add(courseGrade);
                }

                response.put("courseGrades", courseGrades);
            } else {
                response.put("error", "No grades found for the student");
            }
        } catch (Exception e) {
            response.put("error", "An error occurred while fetching grades");
        }

        return response;
    }
}
