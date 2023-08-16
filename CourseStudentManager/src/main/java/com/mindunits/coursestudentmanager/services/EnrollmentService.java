package com.mindunits.coursestudentmanager.services;

import com.mindunits.coursestudentmanager.models.Enrollment;
import com.mindunits.coursestudentmanager.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

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

