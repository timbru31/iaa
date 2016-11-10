package de.nordakademie.iaa_multiple_choice.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.nordakademie.iaa_multiple_choice.domain.Answer;
import de.nordakademie.iaa_multiple_choice.domain.Question;
import de.nordakademie.iaa_multiple_choice.domain.QuestionType;

public class UpdateQuestionAction extends BaseQuestionAction {
    private static final long serialVersionUID = -5586848162334856912L;

    public String editQuestion() {
        setQuestion(getQuestionService().find(getQuestionId()));
        return SUCCESS;
    }

    public String updateQuestion() {
        final Question updatedQuestion = getQuestionService().find(getQuestionId());
        // Update basic data
        updatedQuestion.setText(getQuestion().getText());
        updatedQuestion.setPoints(getQuestion().getPoints());
        if (updatedQuestion.getType() == QuestionType.SINGLE_CHOICE) {
            int i = 0;
            for (final Answer answer : updatedQuestion.getAnswers()) {
                try {
                    final String rawAnswerText = getRawAnswerTextsSc()[i];
                    answer.setText(rawAnswerText);
                    answer.setRightAnswer(i == getSc().intValue());
                    i++;
                    getAnswerService().updateAnswer(answer);
                } catch (final IndexOutOfBoundsException e) {
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
            for (final Answer answer : updatedQuestion.getAnswers()) {
                try {
                    final String rawAnswerText = getRawAnswerTextsMc()[i];
                    answer.setText(rawAnswerText);
                    answer.setRightAnswer(getMc().contains(i));
                    i++;
                    getAnswerService().updateAnswer(answer);
                } catch (final IndexOutOfBoundsException e) {
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
            final Pattern p = Pattern.compile("\\[(.*?)\\]");
            final Matcher m = p.matcher(updatedQuestion.getText());
            while (m.find()) {
                final String rawAnswerText = m.group(1);
                final Answer answer = new Answer(rawAnswerText, true);
                updatedQuestion.addAnswer(answer);
                getAnswerService().createAnswer(answer);
            }
            updatedQuestion.setText(updatedQuestion.getText().replaceAll("\\[(.*?)\\]", "[]"));
        }
        getQuestionService().updateQuestion(updatedQuestion);
        return SUCCESS;
    }
}