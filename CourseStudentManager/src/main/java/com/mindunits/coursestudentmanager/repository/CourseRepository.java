package com.mindunits.coursestudentmanager.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class CourseRepository {

    @PersistenceContext
    EntityManager dataBase;

    @Transactional
    public void saveCourse(String nameCourse, String descriptionCourse, Date starDateCourse, Date endDateCourse, Long idProfessor){
        try {

            String consultaSql = "INSERT INTO course (course_name, description, start_date, end_date, id_professor) VALUES (:nameParametro, :descriptionParametro, :starDateParametro, :endDateParametro, :idProfessorParametro)";

            dataBase.createNativeQuery(consultaSql)
                    .setParameter("nameParametro", nameCourse)
                    .setParameter("descriptionParametro", descriptionCourse)
                    .setParameter("starDateParametro", starDateCourse)
                    .setParameter("endDateParametro", endDateCourse)
                    .setParameter("idProfessorParametro", idProfessor)
                    .executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
