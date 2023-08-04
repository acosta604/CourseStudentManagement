package com.mindunits.coursestudentmanager.controllers;


import com.mindunits.coursestudentmanager.models.Student;
import com.mindunits.coursestudentmanager.repository.StudentRepository;
import com.mindunits.coursestudentmanager.services.StudentService;
import com.mindunits.coursestudentmanager.validators.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class StudentController {

    EmailValidator emailValidator = new EmailValidator();

    @Autowired
    StudentRepository repositorioDeEstudiantes;


    @PostMapping("/api/student")
    public String registrarse(@RequestBody Student student){

        String studentMail = student.getEmail();
        String studentName = student.getName();


        String studentPhone = student.getPhone();

       if(emailValidator.esValido(studentMail) == false){
            return "No es un mail válido";
        }

        repositorioDeEstudiantes.guardarEstudiante(studentName, studentMail, studentPhone);
        return "Estudiante agregado a base de datos.";

    }


    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PutMapping("/api/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        try {
            Student updated = studentService.updateStudent(id, updatedStudent.getName(), updatedStudent.getEmail(), updatedStudent.getPhone());
            return ResponseEntity.ok(updated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
