package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Question;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.service.QuestionService;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@LecturerRequired
public class QuestionAction extends ActionSupport {
    private static final long serialVersionUID = 6954059649443931989L;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ExamService examService;
    @Getter
    @Setter
    private Question question;
    @Getter
    @Setter
    private Long examId;

    public String deleteQuestion() {
        questionService.deleteQuestion(question.getId());
        return SUCCESS;
    }

    public String saveQuestion() {
        final Exam exam = examService.find(examId);
        questionService.createQuestion(question);
        exam.addQuestion(question);
        examService.updateExam(exam);
        return SUCCESS;
    }

    public String updateAnswer() {
        questionService.updateQuestion(question);
        return SUCCESS;
    }

    @Override
    public void validate() {
        if (examId == null) {
            addFieldError("examId", getText("error.examId"));
        }
    }
}
