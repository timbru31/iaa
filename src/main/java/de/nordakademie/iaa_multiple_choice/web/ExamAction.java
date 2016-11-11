package de.nordakademie.iaa_multiple_choice.web;

import java.sql.Date;
import java.time.LocalDate;

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
    @Getter
    @Setter
    private Date rawStartDate;
    @Getter
    @Setter
    private Date rawEndDate;
    @Getter
    @Setter
    private LocalDate startDate;
    @Getter
    @Setter
    private LocalDate endDate;
    @Autowired
    private ExamService examService;
    @Autowired
    private UserService userService;

    public String editExam() {
        exam = examService.find(examId);
        if (!exam.isEditable()) {
            throw new ExamNotEditableException();
        }
        rawStartDate = Date.valueOf(exam.getStartDate());
        rawEndDate = Date.valueOf(exam.getEndDate());
        return SUCCESS;
    }

    public String saveExam() {
        exam.setStartDate(startDate);
        exam.setEndDate(endDate);
        if (examId == null) {
            final Lecturer lecturer = (Lecturer) getUser();
            lecturer.addExam(exam);
            examService.createExam(exam);
            userService.updateUser(lecturer);
            return "created";
        }
        final Exam originalExam = examService.find(examId);
        exam.setTokenList(originalExam.getTokenList());
        exam.setQuestions(originalExam.getQuestions());
        exam.setId(examId);
        examService.updateExam(exam);
        return "updated";
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
        if (exam.getMinPoints() == null || exam.getMinPoints() <= 0 || exam.getMinPoints() > 100) {
            addFieldError("exam.minPoints", getText("validation.minPoints"));
        }
        if (exam.getCreditPoints() == null) {
            addFieldError("exam.creditPoints", getText("validation.creditPoints"));
        }
        if (exam.getEvaluationMethod() == null) {
            addFieldError("exam.evaluationMethod", getText("validation.evaluationMethod"));
        }
        startDate = rawStartDate.toLocalDate();
        endDate = rawEndDate.toLocalDate();
        if (endDate.isBefore(startDate)) {
            addFieldError("exam.endDate", getText("validation.endDate"));
        }
    }
}
