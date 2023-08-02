package com.mindunits.coursestudentmanager.controllers;


import com.mindunits.coursestudentmanager.models.Course;
import com.mindunits.coursestudentmanager.models.Enrollment;
import com.mindunits.coursestudentmanager.models.Student;
import com.mindunits.coursestudentmanager.repository.StudentRepository;
import com.mindunits.coursestudentmanager.validators.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class StudentController {

    EmailValidator emailValidator = new EmailValidator();

    @Autowired
    StudentRepository studentRepository;


    @PostMapping("/api/student")
    public String register(@RequestBody Student student){

        String studentMail = student.getEmail();
        String studentName = student.getName();
        String studentPhone = student.getPhone();

        if(emailValidator.esValido(studentMail) == false){
            return "Not a valid email.";
        }

        studentRepository.saveStudent(studentName, studentMail, studentPhone);

        return "Student added to the database.";

    }
    @DeleteMapping("/api/student/{id}")
    public void deleteCourse(@PathVariable("id") Long id) {
        studentRepository.courseCancellation(id);
    }
    @PostMapping("/api/registerstudentcourses")
    public String registerCourse(@RequestBody Enrollment enrollment) {
        Long idStudent = enrollment.getStudent().getId();
        Long idCourse = enrollment.getCourse().getId();
        Date date = enrollment.getDate();
        String status = enrollment.getStatus();
        studentRepository.courseRegistration(idStudent, idCourse, date, status);
        return "Course successfully enrolled.";
    }
    @GetMapping("/api/student")
    public List<Enrollment> getAll() {
        return studentRepository.getAllEnrollments();
    }

}
