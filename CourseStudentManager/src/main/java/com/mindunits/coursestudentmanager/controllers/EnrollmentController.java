package com.mindunits.coursestudentmanager.controllers;

import com.mindunits.coursestudentmanager.models.Enrollment;
import com.mindunits.coursestudentmanager.services.EnrollmentService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


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
}
