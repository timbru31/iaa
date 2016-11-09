package de.nordakademie.iaa_multiple_choice.web;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.nordakademie.iaa_multiple_choice.domain.Answer;
import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.QuestionType;
import de.nordakademie.iaa_multiple_choice.domain.exceptions.ExamNotEditableException;
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

    private void findExam() {
        final Exam exam = getExamService().find(getExamId());
        if (!exam.isEditable()) {
            throw new ExamNotEditableException();
        }
    }

    @Override
    public String input() {
        findExam();
        return INPUT;
    }

    // public String deleteQuestion() {
    // getQuestionService().deleteQuestion(question.getId());
    // return SUCCESS;
    // }

    public String saveQuestion() {
        getQuestionService().createQuestion(getQuestion());
        getQuestion().setAnswers(new HashSet<>());
        final Exam exam = getExamService().find(getExamId());
        if (getQuestionType().equals("sc")) {
            getQuestion().setType(QuestionType.SINGLE_CHOICE);
            for (int i = 0; i < getRawAnswerTextsSc().length; i++) {
                final String rawAnswerText = getRawAnswerTextsSc()[i];
                final Answer answer = new Answer(rawAnswerText, i == getSc());
                getQuestion().addAnswer(answer);
                getAnswerService().createAnswer(answer);
            }
        } else if (getQuestionType().equals("mc")) {
            getQuestion().setType(QuestionType.MULTIPLE_CHOICE);
            for (int i = 0; i < getRawAnswerTextsMc().length; i++) {
                final String rawAnswerText = getRawAnswerTextsMc()[i];
                final Answer answer = new Answer(rawAnswerText, getMc().contains(i));
                getQuestion().addAnswer(answer);
                getAnswerService().createAnswer(answer);
            }
        } else if (getQuestionType() == null) {
            final Pattern p = Pattern.compile("\\[(.*?)\\]");
            final Matcher m = p.matcher(getQuestion().getText());
            getQuestion().setType(QuestionType.FILL_IN_THE_BLANK);
            while (m.find()) {
                final String rawAnswerText = m.group(1);
                final Answer answer = new Answer(rawAnswerText, true);
                getQuestion().addAnswer(answer);
                getAnswerService().createAnswer(answer);
            }
            getQuestion().setText(getQuestion().getText().replaceAll("\\[(.*?)\\]", "[]"));
        }
        exam.addQuestion(getQuestion());
        getExamService().updateExam(exam);
        return SUCCESS;
    }

    public void validateSaveQuestion() {
        validateQuestion();
    }
}