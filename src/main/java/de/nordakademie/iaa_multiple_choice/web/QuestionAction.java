package de.nordakademie.iaa_multiple_choice.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Answer;
import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Question;
import de.nordakademie.iaa_multiple_choice.domain.exceptions.ExamNotFoundException;
import de.nordakademie.iaa_multiple_choice.service.AnswerService;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.service.QuestionService;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@LecturerRequired
public class QuestionAction extends BaseAction {
    private static final long serialVersionUID = 6954059649443931989L;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ExamService examService;
    @Autowired
    private AnswerService answerService;
    @Getter
    @Setter
    private Question question;
    @Getter
    @Setter
    private Long examId;

    @Getter
    @Setter
    private String rawAnswerTextsSc[]; // SC
    // private String/boolean selected;
    @Getter
    @Setter
    private String rawAnswerTextsMc[]; // MC
    // private String/boolean selected;
    @Getter
    @Setter
    private Integer sc;
    @Getter
    @Setter
    private ArrayList<Integer> mc = new ArrayList<>();
    @Getter
    @Setter
    private String fitb;

    @Getter
    @Setter
    private String questionType;

    public String createQuestion() {
        final Exam exam = examService.find(examId);
        if (exam.getId() == null) {
            throw new ExamNotFoundException();
        }
        return SUCCESS;
    }

    public String deleteQuestion() {
        questionService.deleteQuestion(question.getId());
        return SUCCESS;
    }

    public String saveQuestion() {
        question.setAnswers(new HashSet<>());
        final Exam exam = examService.find(examId);
        if (questionType.equals("sc")) {
            for (int i = 0; i < rawAnswerTextsSc.length; i++) {
                final String rawAnswerText = rawAnswerTextsSc[i];
                final Answer answer = new Answer(rawAnswerText, i == sc);
                question.addAnswer(answer);
                answerService.createAnswer(answer);
            }
        } else if (questionType.equals("mc")) {
            for (int i = 0; i < rawAnswerTextsMc.length; i++) {
                final String rawAnswerText = rawAnswerTextsMc[i];
                final Answer answer = new Answer(rawAnswerText, mc.contains(i));
                question.addAnswer(answer);
                answerService.createAnswer(answer);
            }
        } else if (question.getType() == null) {
            final Pattern p = Pattern.compile("\\[(.*?)\\]");
            final Matcher m = p.matcher(question.getText());
            while (m.find()) {
                final String rawAnswerText = m.group(1);
                final Answer answer = new Answer(rawAnswerText, true);
                question.addAnswer(answer);
                answerService.createAnswer(answer);
            }
        }
        questionService.createQuestion(question);
        exam.addQuestion(question);
        examService.updateExam(exam);
        return SUCCESS;
    }

    public String updateAnswer() {
        questionService.updateQuestion(question);
        return SUCCESS;
    }

    public void validateSaveQuestion() {
        if (question.getText() == "") {
            addFieldError("question.text", getText("validation.questionMissing"));
        }
        if (question.getPoints() == null) {
            addFieldError("question.points", getText("validation.pointsMissing"));
        }
    }
}
