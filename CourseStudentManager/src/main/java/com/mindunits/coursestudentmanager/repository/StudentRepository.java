package com.mindunits.coursestudentmanager.repository;


import com.mindunits.coursestudentmanager.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
