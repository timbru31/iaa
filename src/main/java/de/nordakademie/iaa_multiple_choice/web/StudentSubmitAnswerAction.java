package de.nordakademie.iaa_multiple_choice.web;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Answer;
import de.nordakademie.iaa_multiple_choice.domain.Question;
import de.nordakademie.iaa_multiple_choice.domain.QuestionType;
import de.nordakademie.iaa_multiple_choice.domain.TestResultAnswers;
import de.nordakademie.iaa_multiple_choice.domain.exceptions.QuestionNotFoundException;
import de.nordakademie.iaa_multiple_choice.service.AnswerService;
import de.nordakademie.iaa_multiple_choice.service.TestResultAnswersService;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import de.nordakademie.iaa_multiple_choice.web.util.StudentRequired;
import lombok.Getter;
import lombok.Setter;

/**
 * Action for submitting an answer as a student.
 *
 * @author Yannick Rump
 */
@LoginRequired
@StudentRequired
public class StudentSubmitAnswerAction extends BaseStudentExamAction {
    private static final long serialVersionUID = 7291222426733094490L;
    @Autowired
    private TestResultAnswersService testResultAnswersService;
    @Autowired
    private AnswerService answerService;
    @Getter
    @Setter
    private String[] fillInTheBlankAnswers;
    @Getter
    @Setter
    private Integer sc;
    @Getter
    @Setter
    private ArrayList<Integer> mc = new ArrayList<>();

    /**
     * Helper method to get an entry by it's index from a Set.
     * 
     * @param set the set to search in
     * @param index the wanted index
     * @return the element at this position or null if not found
     */
    private Answer getAnwserByIndex(final Set<? extends Answer> set, final int index) {
        int result = 0;
        for (final Answer answer : set) {
            if (result == index) {
                return answer;
            }
            result++;
        }
        return null;
    }

    public String saveAnswer() {
        final String result = checkPermissions();
        if (!result.equals(SUCCESS)) {
            return result;
        }
        // question check needs to be different!
        if (getQuestionId() == null) {
            throw new QuestionNotFoundException();
        }
        final Optional<Question> optionalQuestion = getExam().getQuestions().stream()
                .filter(q -> getQuestionId() == q.getId()).findFirst();
        if (!optionalQuestion.isPresent()) {
            throw new QuestionNotFoundException();
        }
        setQuestion(optionalQuestion.get());
        final LinkedHashSet<Answer> answerSet = new LinkedHashSet<>();
        if (getQuestion().getType() == QuestionType.FILL_IN_THE_BLANK) {
            extracted(answerSet);
        } else if (getQuestion().getType() == QuestionType.SINGLE_CHOICE) {
            for (int i = 0; i < getQuestion().getAnswers().size(); i++) {
                final Answer correctAnswer = getAnwserByIndex(getQuestion().getAnswers(), i);
                if (getTestResult().getSubmittedAnswers().containsKey(getQuestion())) {
                    final TestResultAnswers testResultAnswers = getTestResult().getSubmittedAnswers()
                            .get(getQuestion());
                    final Answer answer = getAnwserByIndex(testResultAnswers.getAnswers(), i);
                    answer.setRightAnswer(i == sc);
                    answerService.updateAnswer(answer);
                } else {
                    final Answer answer = new Answer(correctAnswer.getText(), i == sc);
                    answerSet.add(answer);
                }
            }
        } else if (getQuestion().getType() == QuestionType.MULTIPLE_CHOICE) {
            for (int i = 0; i < getQuestion().getAnswers().size(); i++) {
                final Answer correctAnswer = getAnwserByIndex(getQuestion().getAnswers(), i);
                if (getTestResult().getSubmittedAnswers().containsKey(getQuestion())) {
                    final TestResultAnswers testResultAnswers = getTestResult().getSubmittedAnswers()
                            .get(getQuestion());
                    final Answer answer = getAnwserByIndex(testResultAnswers.getAnswers(), i);
                    answer.setRightAnswer(mc.contains(i));
                    answerService.updateAnswer(answer);
                } else {
                    final Answer answer = new Answer(correctAnswer.getText(), mc.contains(i));
                    answerSet.add(answer);
                }
            }
        }
        if (!getTestResult().getSubmittedAnswers().containsKey(getQuestion())) {
            // save answers
            final TestResultAnswers testResultAnswers = new TestResultAnswers();
            testResultAnswers.setAnswers(answerSet);
            testResultAnswersService.createTestResultAnswers(testResultAnswers);

            // add in-between object
            final Map<Question, TestResultAnswers> submittedAnswers = getTestResult().getSubmittedAnswers();
            submittedAnswers.put(getQuestion(), testResultAnswers);
            getTestResult().setSubmittedAnswers(submittedAnswers);
            getTestResultService().updateTestResult(getTestResult());
        }
        return SUCCESS;
    }

    private void extracted(final LinkedHashSet<Answer> answerSet) {
        for (int i = 0; i < fillInTheBlankAnswers.length; i++) {
            final String blankAnwser = fillInTheBlankAnswers[i];
            if (getTestResult().getSubmittedAnswers().containsKey(getQuestion())) {
                final TestResultAnswers testResultAnswers = getTestResult().getSubmittedAnswers()
                        .get(getQuestion());
                final Answer answer = getAnwserByIndex(testResultAnswers.getAnswers(), i);
                answer.setText(blankAnwser);
                answerService.updateAnswer(answer);
            } else {
                final Answer answer = new Answer(blankAnwser, true);
                answerSet.add(answer);
            }
        }
    }

}
