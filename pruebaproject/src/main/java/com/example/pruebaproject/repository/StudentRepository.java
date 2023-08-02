package com.example.pruebaproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentRepository {

    @PersistenceContext
    EntityManager baseDeDatos;

    @Transactional
    public void guardarEstudiante(String nameStudent, String emailStudent, String phoneStudent) {
        String consultaSql = "INSERT INTO student (student_name, email, phone) VALUES (?, ?, ?)";
        baseDeDatos.createNativeQuery(consultaSql)
                .setParameter(1, nameStudent)
                .setParameter(2, emailStudent)
                .setParameter(3, phoneStudent)
                .executeUpdate();
    }
}

