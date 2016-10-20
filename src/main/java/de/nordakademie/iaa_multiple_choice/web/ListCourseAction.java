package de.nordakademie.iaa_multiple_choice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Course;
import de.nordakademie.iaa_multiple_choice.service.CourseService;
import lombok.Getter;

public class ListCourseAction extends ActionSupport {

    private static final long serialVersionUID = -3256910191403254866L;

    @Getter
    private List<Course> courses;
    private final CourseService courseService;

    @Autowired
    public ListCourseAction(final CourseService courseService) {
        this.courseService = courseService;
    }

    public String listAll() {
        courses = courseService.listAll();
        return SUCCESS;
    }
}
