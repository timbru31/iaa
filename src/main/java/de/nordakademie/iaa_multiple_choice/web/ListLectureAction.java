package de.nordakademie.iaa_multiple_choice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Lecture;
import de.nordakademie.iaa_multiple_choice.service.LectureService;
import lombok.Getter;

public class ListLectureAction extends ActionSupport {

    private static final long serialVersionUID = 4517744975089615143L;

    @Getter
    private List<Lecture> lectures;
    private final LectureService lectureService;

    @Autowired
    public ListLectureAction(final LectureService lectureService) {
        this.lectureService = lectureService;
    }

    public String listAll() {
        lectures = lectureService.listAll();
        return SUCCESS;
    }
}
