package com.mindunits.coursestudentmanager.repository;


import com.mindunits.coursestudentmanager.models.Course;
import com.mindunits.coursestudentmanager.models.Enrollment;
import com.mindunits.coursestudentmanager.models.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public class StudentRepository {


    @PersistenceContext
    EntityManager database;


    @Transactional
    public void saveStudent(String nameStudent, String emailStudent, String phoneStudent){

        String saveStudentSql = "INSERT INTO Student (student_name, email, phone) VALUES (:nameParametro, :emailParametro, :phoneParametro)";

        database.createQuery(saveStudentSql)
                .setParameter("nameParametro", nameStudent)
                .setParameter("emailParametro", emailStudent)
                .setParameter("phoneParametro", phoneStudent)
                .executeUpdate();
    }

    @Transactional
    public void courseRegistration(Long idStudent, Long idCourse, Date date, String status) {
        String courseRegistrationSql = "INSERT INTO Enrollment (id_student, id_course, enrollment_date, status) " +
                "VALUES (:paramIdStudent, :paramIdCourse, :paramDate, :paramStatus)";
        database.createNativeQuery(courseRegistrationSql)
                .setParameter("paramIdStudent", idStudent)
                .setParameter("paramIdCourse", idCourse)
                .setParameter("paramDate", date)
                .setParameter("paramStatus", status)
                .executeUpdate();
    }
    @Transactional
    public void courseCancellation(Long id){
        String courseCancellationSql = "DELETE FROM Enrollment WHERE id = :paramId";
        database.createQuery(courseCancellationSql)
                .setParameter("paramId", id)
                .executeUpdate();
    }

    @Transactional
    public List<Enrollment> getAllEnrollments(){
        String selectAllSql = "FROM Enrollment";
        return database.createQuery(selectAllSql, Enrollment.class)
                .getResultList();
    }


    //INSERT INTO `coursestudentmanager`.`student`
    // (`student_name`, `email`, `phone`)
    // VALUES ('pipo', 'emailfalop@123.com', '1939392834');


}
