package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
public class CreateExamAction extends ActionSupport {

    private static final long serialVersionUID = -3297218344316923487L;
    @Getter
    @Setter
    private Exam exam;
    private final ExamService examService;

    @Autowired
    public CreateExamAction(ExamService examService) {
        this.examService = examService;
    }

    public String createExam() {
        examService.createExam(exam);
        return SUCCESS;
    }

}
