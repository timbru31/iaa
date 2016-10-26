package de.nordakademie.iaa_multiple_choice.web;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Answer;
import de.nordakademie.iaa_multiple_choice.service.AnswerService;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
public class CreateAnswerAction extends ActionSupport {

    private static final long serialVersionUID = -6868369501706506064L;
    @Getter
    @Setter
    private Answer answer;
    private final AnswerService answerService;

    public CreateAnswerAction(AnswerService answerService) {
        this.answerService = answerService;
    }

    public String createAnswer() {
        answerService.createAnswer(answer);
        return SUCCESS;
    }

}
