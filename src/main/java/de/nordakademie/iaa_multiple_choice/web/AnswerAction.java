package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Answer;
import de.nordakademie.iaa_multiple_choice.service.AnswerService;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@LecturerRequired
public class AnswerAction extends ActionSupport {
    private static final long serialVersionUID = -6868369501706506064L;
    @Getter
    @Setter
    private Answer answer;

    @Autowired
    private AnswerService answerService;

    public String createAnswer() {
        answerService.createAnswer(answer);
        return SUCCESS;
    }

    public String deleteAnswer() {
        answerService.deleteAnswer(answer.getId());
        return SUCCESS;

    }

    public String updateAnswer() {
        answerService.updateAnswer(answer);
        return SUCCESS;
    }
}
