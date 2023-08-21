package com.mindunits.coursestudentmanager.controllers;

import com.mindunits.coursestudentmanager.models.Enrollment;
import com.mindunits.coursestudentmanager.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*") //para cargar los datos de la bd en el front
@RestController
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/api/enrollment")
    public ResponseEntity<Enrollment> saveEnrollment(@RequestBody Enrollment enrollment) {
        try{
            Enrollment save = enrollmentService.saveEnrollment(enrollment);
            return ResponseEntity.ok(save);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/api/enrollment/{id}")
    public ResponseEntity<Enrollment> deleteEnrollment(@PathVariable Long id) {
        try {
            enrollmentService.deleteEnrollmentById(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/enrollment")
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        try {
            List<Enrollment> getAll = enrollmentService.getAllEnrollments();
            return ResponseEntity.ok(getAll);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/enrollment/{id}")
    public ResponseEntity<Enrollment> getIdEnrollment(@PathVariable Long id){
        try {
            Enrollment getEnrollmentId = enrollmentService.getIdEnrollment(id);
            return ResponseEntity.ok(getEnrollmentId);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/enrollment/update/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        try {
            Enrollment updateEnrollment = enrollmentService.updateEnrollment(
                    id,
                    enrollment.getStatus(),
                    enrollment.getDate());
            return ResponseEntity.ok(updateEnrollment);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
