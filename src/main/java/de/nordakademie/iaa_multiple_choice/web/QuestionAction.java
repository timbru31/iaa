package de.nordakademie.iaa_multiple_choice.web;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Answer;
import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Question;
import de.nordakademie.iaa_multiple_choice.service.AnswerService;
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
    @Autowired
    private AnswerService answerService;
    @Getter
    @Setter
    private Question question;
    @Getter
    @Setter
    private Long examId;

    @Getter
    @Setter
    private String rawAnswerTextsSc[]; // SC
    // private String/boolean selected;
    @Getter
    @Setter
    private String rawAnswerTextsMc[]; // MC
    // private String/boolean selected;
    @Getter
    @Setter
    private Integer sc;
    @Getter
    @Setter
    private ArrayList<Integer> mc = new ArrayList<>();

    @Getter
    @Setter
    private String questionType;

    public String deleteQuestion() {
        questionService.deleteQuestion(question.getId());
        return SUCCESS;
    }

    public String saveQuestion() {
        question.setAnswers(new HashSet<>());
        final Exam exam = examService.find(examId);
        if (questionType.equals("sc")) {
            for (int i = 0; i < rawAnswerTextsSc.length; i++) {
                final String rawAnswerText = rawAnswerTextsSc[i];
                final Answer answer = new Answer(rawAnswerText, i == sc);
                question.addAnswer(answer);
                answerService.createAnswer(answer);
            }
        } else if (questionType.equals("mc")) {
            for (int i = 0; i < rawAnswerTextsMc.length; i++) {
                final String rawAnswerText = rawAnswerTextsMc[i];
                final Answer answer = new Answer(rawAnswerText, mc.contains(i));
                question.addAnswer(answer);
                answerService.createAnswer(answer);
            }
        }
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
