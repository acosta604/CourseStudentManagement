package com.mindunits.coursestudentmanager.services;

import com.mindunits.coursestudentmanager.models.Course;
import com.mindunits.coursestudentmanager.models.Enrollment;
import com.mindunits.coursestudentmanager.repository.CourseRepositoryPost;
import com.mindunits.coursestudentmanager.repository.EnrollmentRepository;
import com.mindunits.coursestudentmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseRepositoryPost courseRepositoryPost;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollmentById(Long id) {
         enrollmentRepository.deleteById(id);
    }



    public Map<String, String> getEnrollmentDetailsById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Enrollment not found"));

        Map<String, String> enrollmentDetails = new HashMap<>();
        enrollmentDetails.put("courseName", enrollment.getCourse().getName());
        enrollmentDetails.put("studentName", enrollment.getStudent().getName());
        enrollmentDetails.put("status", enrollment.getStatus());

        return enrollmentDetails;
    }


    public List<Map<String, String>> getCoursesByStudentId(Long studentId) {
        List<Map<String, String>> courses = new ArrayList<>();

        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);

        for (Enrollment enrollment : enrollments) {
            Long courseId = enrollment.getCourse().getId();
            Course course = courseRepositoryPost.findById(courseId)
                    .orElseThrow(() -> new NoSuchElementException("Course not found"));

            Map<String, String> courseInfo = new HashMap<>();
            courseInfo.put("courseId", courseId.toString());
            courseInfo.put("courseName", course.getName());
            courses.add(courseInfo);
        }

        return courses;
    }



    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getIdEnrollment(Long id) {
        return enrollmentRepository.getById(id);
    }

    public Enrollment updateEnrollment(Long id, String statusEnrollment, Date dateEnrollment){
        Enrollment existingEnrollment = enrollmentRepository.findById(id).orElse(null);

        if (existingEnrollment == null) {
            throw new NoSuchElementException(String.format("Enrollment with ID %s not found", id));
        }
        System.out.println();
        existingEnrollment.setStatus(statusEnrollment == null ? existingEnrollment.getStatus() : statusEnrollment);
        existingEnrollment.setDate(dateEnrollment == null ? existingEnrollment.getDate() : dateEnrollment);
        existingEnrollment.setCourse(existingEnrollment.getCourse());
        existingEnrollment.setStudent(existingEnrollment.getStudent());

        return enrollmentRepository.save(existingEnrollment);
    }

}

