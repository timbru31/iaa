package de.nordakademie.iaa_multiple_choice.web;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Question;
import de.nordakademie.iaa_multiple_choice.service.QuestionService;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
public class CreateQuestionAction extends ActionSupport {

    private static final long serialVersionUID = 6954059649443931989L;
    @Getter
    @Setter
    private Question question;
    private final QuestionService questionService;

    public CreateQuestionAction(QuestionService questionService) {
        this.questionService = questionService;
    }

    public String createQuestion() {
        questionService.createQuestion(question);
        return SUCCESS;
    }

}
