package de.nordakademie.iaa_multiple_choice.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Question;
import de.nordakademie.iaa_multiple_choice.domain.exceptions.ExamNotEditableException;
import de.nordakademie.iaa_multiple_choice.service.AnswerService;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.service.QuestionService;
import de.nordakademie.iaa_multiple_choice.web.util.LecturerRequired;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import lombok.Getter;
import lombok.Setter;

/**
 * Action for question validation.
 *
 * @author Tim Brust
 */
@LoginRequired
@LecturerRequired
public abstract class BaseQuestionAction extends BaseAction {
    private static final long serialVersionUID = -8038161807389821278L;
    public static final Pattern FILL_IN_THE_BLANK_PATTERN = Pattern.compile("\\[(.+?)\\]");
    @Autowired
    @Getter
    private QuestionService questionService;
    @Autowired
    @Getter
    private ExamService examService;
    @Autowired
    @Getter
    private AnswerService answerService;
    @Getter
    @Setter
    private Long examId;
    @Getter
    @Setter
    private Long questionId;
    @Getter
    @Setter
    private String rawAnswerTextsSc[];
    @Getter
    @Setter
    private String rawAnswerTextsMc[];
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
    private Question question;
    @Getter
    @Setter
    private Exam exam;

    /**
     * Checks for a valid and editable exam.
     *
     * @return the exam
     */
    public Exam findExam() {
        exam = getExamService().find(getExamId());
        if (!exam.isEditable()) {
            throw new ExamNotEditableException();
        }
        return exam;
    }

    /**
     * Validates a question.
     */
    public void validateQuestion() {
        if (question == null) {
            addFieldError("question.text", getText("validation.questionMissing"));
            return;
        }
        if (question.getText() == null || question.getText().isEmpty()) {
            addFieldError("question.text", getText("validation.questionMissing"));
        }
        if (question.getPoints() == null || question.getPoints() <= 0) {
            addFieldError("question.points", getText("validation.pointsMissing"));
        }
        if (question.getType() == null) {
            addFieldError("question.type", getText("validation.questionTypeMissing"));
        } else {
            switch (question.getType()) {
            case SINGLE_CHOICE:
                checkSIngleChoiceQuestion();
                break;
            case MULTIPLE_CHOICE:
                checkMultipleChoiceQuestion();
                break;
            case FILL_IN_THE_BLANK:
                checkFillInTheBlankQuestion();
                break;
            default:
                addFieldError("question.type", getText("validation.questionTypeMissing"));
                break;
            }
        }
    }

    /**
     * Checks that a least one blank is present.
     */
    private void checkFillInTheBlankQuestion() {
        final Matcher matcher = FILL_IN_THE_BLANK_PATTERN.matcher(question.getText());
        if (!matcher.find()) {
            addFieldError("question.fillInTheBlank", getText("validation.noBlank"));
        }
        if (question.getFormattedQuestionText().equals("[]")) {
            addFieldError("question.fillInTheBlank", getText("validation.noText"));
        }
    }

    /**
     * Checks that there is at least one question and that each text is not empty.
     */
    private void checkMultipleChoiceQuestion() {
        if (rawAnswerTextsMc == null || rawAnswerTextsMc.length < 1) {
            addFieldError("question.multipleChoice", getText("validation.multipleChoiceMinOneAnswer"));
        } else if (Arrays.stream(rawAnswerTextsMc).anyMatch(text -> text == null || text.isEmpty())) {
            addFieldError("question.multipleChoice", getText("validation.answerEmpty"));
        }
    }

    /**
     * Checks that there is at least one question, one choice and that each text is not empty.
     */
    private void checkSIngleChoiceQuestion() {
        if (sc == null) {
            addFieldError("question.singleChoice", getText("validation.singleChoiceValue"));
        }
        if (rawAnswerTextsSc == null || rawAnswerTextsSc.length < 2) {
            addFieldError("question.singleChoice", getText("validation.singleChoiceMinTwoAnswers"));
        } else if (Arrays.stream(rawAnswerTextsSc).anyMatch(text -> text == null || text.isEmpty())) {
            addFieldError("question.singleChoice", getText("validation.answerEmpty"));
        }
    }
}
