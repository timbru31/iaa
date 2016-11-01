package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Lecturer;
import de.nordakademie.iaa_multiple_choice.domain.exceptions.ExamNotEditableException;
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
        if (!exam.isEditable()) {
            throw new ExamNotEditableException();
        }
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
            examService.updateExam(exam);
            return "updated";
        }
    }

    public void validateSaveExam() {
        if (exam == null) {
            addFieldError("exam", getText("validation.exam"));
            return;
        }
        if (exam.getName() == null || exam.getName().isEmpty()) {
            addFieldError("exam.name", getText("validation.examName"));
        }
        if (exam.getExamTime() == null) {
            addFieldError("exam.examTime", getText("validation.examTime"));
        }
        if (exam.getMinPoints() == null) {
            addFieldError("exam.minPoints", getText("validation.minPoints"));
        }
        // if (exam.getCreditPoints() == null || !(exam.getCreditPoints().equals(0.5))
        // || !(exam.getCreditPoints().equals(0.75)) || !(exam.getCreditPoints().equals(1))) {
        // addFieldError("exam.creditPoints", getText("validation.creditPoints"));
        // }
        if (exam.getFinalSubmitDate() != null && exam.getStartDate() != null
                && exam.getFinalSubmitDate().before(exam.getStartDate())) {
            addFieldError("exam.finalSubmitDate", getText("validation.finalSubmitDate"));
        }
    }
}
