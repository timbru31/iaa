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
    @Getter
    @Setter
    private Long examId;
    @Autowired
    private ExamService examService;
    @Autowired
    private UserService userService;

    public String editExam() {
        exam = examService.find(examId);
        return SUCCESS;
    }

    public String saveExam() {
        if (examId == null) {
            final Lecturer lecturer = (Lecturer) getUser();
            lecturer.addExam(exam);
            examService.createExam(exam);
            userService.updateUser(lecturer);
            return "created";
        } else {
            exam.setId(examId);
            exam.setEditable(true);
            examService.updateExam(exam);
            return "updated";
        }
    }
}
