package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Lecture;
import de.nordakademie.iaa_multiple_choice.service.LectureService;
import lombok.Getter;
import lombok.Setter;

public class EditLectureAction extends ActionSupport {

    private static final long serialVersionUID = 8782175539762858416L;

    @Getter
    @Setter
    private Lecture lecture;

    @Getter
    @Setter
    private String courseNaturalId;

    @Getter
    @Setter
    private String roomNaturalId;

    private final LectureService lectureService;

    @Autowired
    public EditLectureAction(final LectureService lectureService) {
        this.lectureService = lectureService;
    }

    public String saveLecture() {
        lectureService.saveLecture(lecture, roomNaturalId, courseNaturalId);
        return SUCCESS;
    }
}
