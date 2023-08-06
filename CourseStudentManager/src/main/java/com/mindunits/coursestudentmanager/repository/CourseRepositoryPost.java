package com.mindunits.coursestudentmanager.repository;

import com.mindunits.coursestudentmanager.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepositoryPost extends JpaRepository<Course, Long> {
}
