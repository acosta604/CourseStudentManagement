package com.mindunits.coursestudentmanager.repository;

import com.mindunits.coursestudentmanager.models.Course;
import com.mindunits.coursestudentmanager.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudentId(Long studentId);
}
