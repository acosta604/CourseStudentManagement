package com.mindunits.coursestudentmanager.controllers;


import com.mindunits.coursestudentmanager.models.Students;
import com.mindunits.coursestudentmanager.repository.StudentRepository;
import com.mindunits.coursestudentmanager.validators.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    EmailValidator emailValidator = new EmailValidator();

    @Autowired
    StudentRepository repositorioDeEstudiantes;


    @PostMapping("/api/student")
    public String registrarse(@RequestBody Students student){

        String studentMail = student.getEmail();
        String studentName = student.getStudent_name();
        String studentPhone = student.getPhone();

        if(emailValidator.esValido(studentMail) == false){
            return "No es un mail válido";
        }

        repositorioDeEstudiantes.guardarEstudiante(studentName, studentMail, studentPhone);

        return "Estudiante agregado a base de datos.";

    }



}