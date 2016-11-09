package de.nordakademie.iaa_multiple_choice.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

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
public abstract class BaseQuestionAction extends BaseAction {
    private static final long serialVersionUID = -8038161807389821278L;
    @Autowired
    @Getter
    private QuestionService questionService;
    @Autowired
    @Getter
    private ExamService examService;
    @Autowired
    @Getter
    private AnswerService answerService;
    @Getter
    @Setter
    private Long examId;
    @Getter
    @Setter
    private Long questionId;
    @Getter
    @Setter
    private String rawAnswerTextsSc[];
    @Getter
    @Setter
    private String rawAnswerTextsMc[];
    @Getter
    @Setter
    private Integer sc;
    @Getter
    @Setter
    private ArrayList<Integer> mc = new ArrayList<>();
    @Getter
    @Setter
    private String fitb;
    @Getter
    @Setter
    private String questionType;
    @Getter
    @Setter
    private Question question;

    public void validateQuestion() {
        if (question == null || question.getText() == null || question.getText().isEmpty()) {
            addFieldError("question.text", getText("validation.questionMissing"));
        }
        if (question.getPoints() == null || question.getPoints().doubleValue() <= 0) {
            addFieldError("question.points", getText("validation.pointsMissing"));
        }
    }
}