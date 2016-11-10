package de.nordakademie.iaa_multiple_choice.web;

import java.util.HashSet;
import java.util.regex.Matcher;

import de.nordakademie.iaa_multiple_choice.domain.Answer;
import de.nordakademie.iaa_multiple_choice.domain.QuestionType;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;

@LoginRequired
@LecturerRequired
public class CreateQuestionAction extends BaseQuestionAction {
    private static final long serialVersionUID = 6954059649443931989L;

    public String createQuestion() {
        findExam();
        return SUCCESS;
    }

    @Override
    public String input() {
        findExam();
        return INPUT;
    }

    public String saveQuestion() {
        getQuestionService().createQuestion(getQuestion());
        getQuestion().setAnswers(new HashSet<>());
        if (getQuestion().getType() == QuestionType.SINGLE_CHOICE) {
            for (int i = 0; i < getRawAnswerTextsSc().length; i++) {
                final String rawAnswerText = getRawAnswerTextsSc()[i];
                final Answer answer = new Answer(rawAnswerText, i == getSc().intValue());
                getQuestion().addAnswer(answer);
                getAnswerService().createAnswer(answer);
            }
        } else if (getQuestion().getType() == QuestionType.MULTIPLE_CHOICE) {
            for (int i = 0; i < getRawAnswerTextsMc().length; i++) {
                final String rawAnswerText = getRawAnswerTextsMc()[i];
                final Answer answer = new Answer(rawAnswerText, getMc().contains(i));
                getQuestion().addAnswer(answer);
                getAnswerService().createAnswer(answer);
            }
        } else if (getQuestion().getType() == QuestionType.FILL_IN_THE_BLANK) {
            final Matcher matcher = FILL_IN_THE_BLANK_PATTERN.matcher(getQuestion().getText());
            while (matcher.find()) {
                final String rawAnswerText = matcher.group(1);
                final Answer answer = new Answer(rawAnswerText, true);
                getQuestion().addAnswer(answer);
                getAnswerService().createAnswer(answer);
            }
            getQuestion().setText(getQuestion().getText().replaceAll("\\[(.*?)\\]", "[]"));
        } else {
            addFieldError("question.type", getText("validation.questionTypeMissing"));
            return INPUT;
        }
        getExam().addQuestion(getQuestion());
        getExamService().updateExam(getExam());
        return SUCCESS;
    }

    public void validateSaveQuestion() {
        validateQuestion();
    }
}
