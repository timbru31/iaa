package de.nordakademie.iaa_multiple_choice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.nordakademie.iaa_multiple_choice.domain.Course;
import de.nordakademie.iaa_multiple_choice.domain.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(final CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void saveCourse(final Course course) {
        courseRepository.createCourse(course);
    }

    @Transactional(readOnly = true)
    public List<Course> listAll() {
        return courseRepository.findAll();
    }
}
