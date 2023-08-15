package com.mindunits.coursestudentmanager.repository;

import com.mindunits.coursestudentmanager.models.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class NoteServiceImpl {

    @Service
    public class       GradeServiceImpl implements  GradeService {
        @Autowired
        private GradeServiceImpl gradeRepository;

        @Autowired
        private StudentRepository studentRepository;

        @Autowired
        private CourseRepository courseRepository;

        @Autowired
        public void addGrade(Long studentId, Long courseId, Double value) {
            // Implement logic to add a new grade for a student and a course
        }

        @Override
        public void updateGrade(Long gradeId, Double value) {
            // Implement logic to update the value of an existing grade
        }

        @Override
        public List<Grade> getGradesByStudent(Long studentId) {
            // Implement logic to get grades for a student
        }

        @Override
        public List<Grade> getGradesByCourse(Long courseId) {
            // Implement logic to get grades for a course


        // Implement other interface methods