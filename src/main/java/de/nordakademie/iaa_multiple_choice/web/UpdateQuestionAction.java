package de.nordakademie.iaa_multiple_choice.web;

import java.util.Iterator;
import java.util.regex.Matcher;

import de.nordakademie.iaa_multiple_choice.domain.Answer;
import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Question;
import de.nordakademie.iaa_multiple_choice.domain.QuestionType;
import de.nordakademie.iaa_multiple_choice.domain.exceptions.QuestionNotFoundException;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;

@LoginRequired
@LecturerRequired
public class UpdateQuestionAction extends BaseQuestionAction {
    private static final long serialVersionUID = -5586848162334856912L;

    public String editQuestion() {
        findQuestion();
        return SUCCESS;
    }

    private void findQuestion() {
        final Exam exam = findExam();
        final Question question = getQuestionService().find(getQuestionId());
        if (!exam.hasQuestion(question)) {
            throw new QuestionNotFoundException();
        }
        setQuestion(question);
    }

    @Override
    public String input() {
        findQuestion();
        return INPUT;
    }

    public String updateQuestion() {
        final Question updatedQuestion = getQuestionService().find(getQuestionId());
        // Update basic data
        updatedQuestion.setText(getQuestion().getText());
        updatedQuestion.setPoints(getQuestion().getPoints());
        if (updatedQuestion.getType() == QuestionType.SINGLE_CHOICE) {
            int i = 0;
            for (Iterator<Answer> iterator = updatedQuestion.getAnswers().iterator(); iterator.hasNext();) {
                Answer answer = iterator.next();
                try {
                    final String rawAnswerText = getRawAnswerTextsSc()[i];
                    answer.setText(rawAnswerText);
                    answer.setRightAnswer(i == getSc());
                    i++;
                    getAnswerService().updateAnswer(answer);
                } catch (final IndexOutOfBoundsException e) {
                    iterator.remove();
                    updatedQuestion.removeAnswer(answer);
                }
            }
            for (; i < getRawAnswerTextsSc().length; i++) {
                final String rawAnswerText = getRawAnswerTextsSc()[i];
                final Answer newAnswer = new Answer(rawAnswerText, i == getSc());
                updatedQuestion.addAnswer(newAnswer);
                getAnswerService().createAnswer(newAnswer);
            }
        } else if (updatedQuestion.getType() == QuestionType.MULTIPLE_CHOICE) {
            int i = 0;
            for (Iterator<Answer> iterator = updatedQuestion.getAnswers().iterator(); iterator.hasNext();) {
                Answer answer = iterator.next();
                try {
                    final String rawAnswerText = getRawAnswerTextsMc()[i];
                    answer.setText(rawAnswerText);
                    answer.setRightAnswer(getMc().contains(i));
                    i++;
                    getAnswerService().updateAnswer(answer);
                } catch (final IndexOutOfBoundsException e) {
                    iterator.remove();
                    updatedQuestion.removeAnswer(answer);
                }
            }
            for (; i < getRawAnswerTextsMc().length; i++) {
                final String rawAnswerText = getRawAnswerTextsMc()[i];
                final Answer newAnswer = new Answer(rawAnswerText, getMc().contains(i));
                updatedQuestion.addAnswer(newAnswer);
                getAnswerService().createAnswer(newAnswer);
            }
        } else if (updatedQuestion.getType() == QuestionType.FILL_IN_THE_BLANK) {
            // It's easier to delete old answers first
            updatedQuestion.getAnswers().clear();
            final Matcher matcher = FILL_IN_THE_BLANK_PATTERN.matcher(updatedQuestion.getText());
            while (matcher.find()) {
                final String rawAnswerText = matcher.group(1);
                final Answer answer = new Answer(rawAnswerText, true);
                updatedQuestion.addAnswer(answer);
                getAnswerService().createAnswer(answer);
            }
        }
        getQuestionService().updateQuestion(updatedQuestion);
        return SUCCESS;
    }

    public void validateUpdateQuestion() {
        validateQuestion();
    }
}
