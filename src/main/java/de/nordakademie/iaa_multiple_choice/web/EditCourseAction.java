package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Course;
import de.nordakademie.iaa_multiple_choice.service.CourseService;
import lombok.Getter;
import lombok.Setter;

public class EditCourseAction extends ActionSupport {

    private static final long serialVersionUID = -5352599443575168936L;

    @Getter
    @Setter
    private Course course;
    private final CourseService courseService;

    @Autowired
    public EditCourseAction(final CourseService courseService) {
        this.courseService = courseService;
    }

    public String saveCourse() {
        courseService.saveCourse(course);
        return SUCCESS;
    }
}
