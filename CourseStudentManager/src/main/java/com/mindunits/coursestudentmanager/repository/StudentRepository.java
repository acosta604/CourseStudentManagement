package com.mindunits.coursestudentmanager.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentRepository {


    @PersistenceContext
    EntityManager baseDeDatos;


    @Transactional
    public void guardarEstudiante(String nameStudent, String emailStudent, String phoneStudent){

        String consultaSql = "INSERT INTO Students (student_name, email, phone) VALUES (:nameParametro, :emailParametro, :phoneParametro)";

        baseDeDatos.createQuery(consultaSql)
                .setParameter("nameParametro", nameStudent)
                .setParameter("emailParametro", emailStudent)
                .setParameter("phoneParametro", phoneStudent)
                .executeUpdate();
    }




    //INSERT INTO `coursestudentmanager`.`student`
    // (`student_name`, `email`, `phone`)
    // VALUES ('pipo', 'emailfalop@123.com', '1939392834');


}
