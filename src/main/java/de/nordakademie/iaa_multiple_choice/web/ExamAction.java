package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.service.UserService;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@LecturerRequired
public class ExamAction extends BaseSessionAction {
    private static final long serialVersionUID = -3297218344316923487L;
    @Getter
    @Setter
    private Exam exam;
    @Autowired
    private ExamService examService;
    @Autowired
    private UserService userService;

    public String saveExam() {
        final Lecturer lecturer = (Lecturer) getUser();
        lecturer.addExam(exam);
        examService.createExam(exam);
        userService.updateUser(lecturer);
        return SUCCESS;
    }

    public String updateExam() {
        examService.updateExam(exam);
        return SUCCESS;
    }
}
