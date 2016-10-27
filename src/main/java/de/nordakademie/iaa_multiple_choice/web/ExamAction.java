package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@LecturerRequired
public class ExamAction extends ActionSupport {

    private static final long serialVersionUID = -3297218344316923487L;
    @Getter
    @Setter
    private Exam exam;
    private final ExamService examService;

    @Autowired
    public ExamAction(final ExamService examService) {
        this.examService = examService;
    }

    public String createExam() {
        examService.createExam(exam);
        return SUCCESS;
    }

    public String display() {
        return SUCCESS;
    }

    public String updateExam() {
        examService.updateExam(exam);
        return SUCCESS;
    }
}
