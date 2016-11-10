package de.nordakademie.iaa_multiple_choice.web;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Question;
import de.nordakademie.iaa_multiple_choice.domain.exceptions.QuestionNotFoundException;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@LecturerRequired
public class DeleteQuestionAction extends BaseQuestionAction {
    private static final long serialVersionUID = -4917089234241389791L;
    @Getter
    @Setter
    private Long newQuestionId;

    public String deleteQuestion() {
        final Exam exam = findExam();
        final Long questionId = getQuestionId();
        final Question question = getQuestionService().find(questionId);
        if (!exam.hasQuestion(question)) {
            throw new QuestionNotFoundException();
        }
        String result = "editQuestion";
        // if there are no more questions left, go to createQuestion
        if (exam.getQuestions().size() == 1) {
            result = "createQuestion";
        } else if (exam.isFirstQuestion(question)) {
            newQuestionId = exam.getNextQuestion(question).getId();
        } else {
            newQuestionId = exam.getPreviousQuestion(question).getId();
        }
        exam.removeQuestion(question);
        getExamService().updateExam(exam);
        return result;
    }
}
