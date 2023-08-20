package com.mindunits.coursestudentmanager.services;

import com.mindunits.coursestudentmanager.models.Course;
import com.mindunits.coursestudentmanager.models.Professor;
import com.mindunits.coursestudentmanager.repository.CourseRepositoryPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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

    public Course updateCourse(Long id, String nameCourse, String descriptionCourse, Date starDateCourse, Date endDateCourse) {
        Course existingCourse = courseRepositoryPost.findById(id).orElse(null);

        if (existingCourse == null) {
            throw new NoSuchElementException("Course with ID " + id + " not found");
        }
        System.out.println();
        existingCourse.setName(nameCourse == null ? existingCourse.getName() : nameCourse);
        existingCourse.setDescription(descriptionCourse == null ? existingCourse.getDescription() : descriptionCourse);
        existingCourse.setStartDate(starDateCourse == null ? existingCourse.getStartDate() :  starDateCourse);
        existingCourse.setEndDate(endDateCourse == null ? existingCourse.getEndDate() : endDateCourse);
        existingCourse.setProfessor(existingCourse.getProfessor());

        return courseRepositoryPost.save(existingCourse);

    }

    public String getProfessorNameByCourseId(Long courseId) {
        Course course = courseRepositoryPost.findById(courseId)
                .orElseThrow(NoSuchElementException::new);

        String professorName = course.getProfessor().getName();
        return professorName;
    }

}
