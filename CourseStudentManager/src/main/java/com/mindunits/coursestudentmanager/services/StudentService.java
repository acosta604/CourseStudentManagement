package com.mindunits.coursestudentmanager.services;

import com.mindunits.coursestudentmanager.models.Student;
import com.mindunits.coursestudentmanager.repository.StudentRepositoryUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@Service
public class StudentService {
    private final StudentRepositoryUpdate studentRepositoryUpdate;

    @Autowired
    public StudentService(StudentRepositoryUpdate studentRepositoryUpdate) {
        this.studentRepositoryUpdate = studentRepositoryUpdate;
    }
    public Student updateStudent(Long id, String name, String email, String phone) {
        Student existingStudent = studentRepositoryUpdate.findById(id).orElse(null);

        if (existingStudent == null) {
            throw new NoSuchElementException("Student with ID " + id + " not found");
        }

        existingStudent.setName(name);
        existingStudent.setEmail(email);
        existingStudent.setPhone(phone);

        return studentRepositoryUpdate.save(existingStudent);
    }
}
