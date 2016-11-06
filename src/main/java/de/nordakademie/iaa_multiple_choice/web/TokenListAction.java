package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@LecturerRequired
public class TokenListAction extends BaseSessionAction {
    private static final long serialVersionUID = -8623838284788636915L;
    @Autowired
    private ExamService examService;
    @Getter
    @Setter
    private Long examId;
    @Getter
    @Setter
    private Exam exam;

    @Override
    public String execute() {
        if (!isMailerDisabled()) {
            return "redirectHome";
        }
        exam = examService.find(examId);
        return SUCCESS;
    }
}
