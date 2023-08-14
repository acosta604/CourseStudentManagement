package com.mindunits.coursestudentmanager.controllers;

import com.mindunits.coursestudentmanager.models.Course;
import com.mindunits.coursestudentmanager.repository.CourseRepository;
import com.mindunits.coursestudentmanager.services.CourseService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.plugins.tiff.TIFFTag;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
@CrossOrigin(origins = "*") //para cargar los datos de la bd en el front
@RestController
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @PostMapping("/api/course")
    public String saveCourse(@RequestBody Course course){

        String courseName = course.getName();
        String courseDescription = course.getDescription();
        Date courseStarDate = course.getStartDate();
        Date courseEndDate = course.getEndDate();
        Long courseIdProfessor = course.getProfessor().getId();

        courseRepository.saveCourse(courseName, courseDescription, courseStarDate, courseEndDate, courseIdProfessor);
        return "curso agregado";
    }

    @GetMapping("/api/course")
    public ResponseEntity<List<Course>> getAllCourse(){
        try {
            List<Course> getAllCourses = courseService.getAllCourses();
            return ResponseEntity.ok(getAllCourses);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/course/{id}")
    public ResponseEntity<Course> getIdCourse(@PathVariable Long id){
        try {
            Course getIdCourse = courseService.getIdCourse(id);
            return ResponseEntity.ok(getIdCourse);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/course/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id){
        try {
            courseService.deleteCourseById(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/course/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course){
        try {
            Course updatedCourse = courseService.updateCourse(id,
                                                              course.getName(),
                                                              course.getDescription(),
                                                              course.getStartDate(),
                                                              course.getEndDate());
            return ResponseEntity.ok(updatedCourse);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }
}
