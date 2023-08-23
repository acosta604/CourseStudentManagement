package com.mindunits.coursestudentmanager.services;

import com.mindunits.coursestudentmanager.models.Course;
import com.mindunits.coursestudentmanager.models.Professor;
import com.mindunits.coursestudentmanager.repository.CourseRepositoryPost;
import com.mindunits.coursestudentmanager.repository.ProfessorRepositoryPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepositoryPost courseRepositoryPost;

    @Autowired
    public CourseService(CourseRepositoryPost courseRepositoryPost) {
        this.courseRepositoryPost = courseRepositoryPost;
    }
    /**
    public Course saveCourse(Course course){
        return courseRepositoryPost.save(course);
    }*/

    public List<Course> getAllCourses() {
        return courseRepositoryPost.findAll();
    }

    public Course getIdCourse(Long id) {
        return courseRepositoryPost.getById(id);
    }

    public void deleteCourseById(Long id) {
        courseRepositoryPost.deleteById(id);
    }

    @Autowired
    private ProfessorRepositoryPost professorRepositoryPost;

    public Course updateCourse(Long id, String nameCourse, String descriptionCourse, Date startDateCourse, Date endDateCourse, Long newProfessorId) {
        Course existingCourse = courseRepositoryPost.findById(id).orElse(null);

        if (existingCourse == null) {
            throw new NoSuchElementException("Course with ID " + id + " not found");
        }

        existingCourse.setName(nameCourse == null ? existingCourse.getName() : nameCourse);
        existingCourse.setDescription(descriptionCourse == null ? existingCourse.getDescription() : descriptionCourse);
        existingCourse.setStartDate(startDateCourse == null ? existingCourse.getStartDate() : startDateCourse);
        existingCourse.setEndDate(endDateCourse == null ? existingCourse.getEndDate() : endDateCourse);

        // Obtén el nuevo profesor por su ID
        Optional<Professor> newProfessorOptional = professorRepositoryPost.findById(newProfessorId);
        if (newProfessorOptional.isPresent()) {
            existingCourse.setProfessor(newProfessorOptional.get());
        } else {
            throw new NoSuchElementException("Professor with ID " + newProfessorId + " not found");
        }

        return courseRepositoryPost.save(existingCourse);
    }


    public String getProfessorNameByCourseId(Long courseId) {
        Course course = courseRepositoryPost.findById(courseId)
                .orElseThrow(NoSuchElementException::new);

        String professorName = course.getProfessor().getName();
        return professorName;
    }

}
