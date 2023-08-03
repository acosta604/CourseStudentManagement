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
        try {
            String consultaSql = "INSERT INTO Student (name, email, phone) VALUES (:nameParametro, :emailParametro, :phoneParametro)";
            baseDeDatos.createQuery(consultaSql)
                    .setParameter("nameParametro", nameStudent)
                    .setParameter("emailParametro", emailStudent)
                    .setParameter("phoneParametro", phoneStudent)
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el estudiante en la base de datos.");
        }
    }


}
