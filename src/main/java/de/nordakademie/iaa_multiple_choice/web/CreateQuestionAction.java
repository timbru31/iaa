package de.nordakademie.iaa_multiple_choice.web;

import java.util.HashSet;
import java.util.regex.Matcher;

import de.nordakademie.iaa_multiple_choice.domain.Answer;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;

/**
 * Action for creating questions.
 *
 * @author Hannes Peterson
 * @author Yannick Rump
 */
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
        findExam();
        getQuestionService().createQuestion(getQuestion());
        getQuestion().setAnswers(new HashSet<>());
        switch (getQuestion().getType()) {
        case SINGLE_CHOICE:
            createSingleChoiceQuestion();
            break;
        case MULTIPLE_CHOICE:
            createMultipleChoiceQuestion();
            break;
        case FILL_IN_THE_BLANK:
            createFillInTheBlankQuestion();
            break;
        default:
            addFieldError("question.type", getText("validation.questionTypeMissing"));
            return INPUT;
        }
        getExam().addQuestion(getQuestion());
        getExamService().updateExam(getExam());
        return SUCCESS;
    }

    private void createSingleChoiceQuestion() {
        for (int i = 0; i < getRawAnswerTextsSc().length; i++) {
            final String rawAnswerText = getRawAnswerTextsSc()[i];
            final Answer answer = new Answer(rawAnswerText, i == getSc());
            getQuestion().addAnswer(answer);
            getAnswerService().createAnswer(answer);
        }
    }

    private void createMultipleChoiceQuestion() {
        for (int i = 0; i < getRawAnswerTextsMc().length; i++) {
            final String rawAnswerText = getRawAnswerTextsMc()[i];
            final Answer answer = new Answer(rawAnswerText, getMc().contains(i));
            getQuestion().addAnswer(answer);
            getAnswerService().createAnswer(answer);
        }
    }

    private void createFillInTheBlankQuestion() {
        final Matcher matcher = FILL_IN_THE_BLANK_PATTERN.matcher(getQuestion().getText());
        while (matcher.find()) {
            final String rawAnswerText = matcher.group(1);
            final Answer answer = new Answer(rawAnswerText, true);
            getQuestion().addAnswer(answer);
            getAnswerService().createAnswer(answer);
        }
    }

    public void validateSaveQuestion() {
        validateQuestion();
    }
}
