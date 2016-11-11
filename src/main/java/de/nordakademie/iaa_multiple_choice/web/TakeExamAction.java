package de.nordakademie.iaa_multiple_choice.web;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Answer;
import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Question;
import de.nordakademie.iaa_multiple_choice.domain.QuestionType;
import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.domain.TestResult;
import de.nordakademie.iaa_multiple_choice.domain.TestResultAnswers;
import de.nordakademie.iaa_multiple_choice.domain.exceptions.QuestionNotFoundException;
import de.nordakademie.iaa_multiple_choice.domain.exceptions.StudentNotEnrolledException;
import de.nordakademie.iaa_multiple_choice.service.AnswerService;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.service.TestResultAnswersService;
import de.nordakademie.iaa_multiple_choice.service.TestResultService;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import de.nordakademie.iaa_multiple_choice.web.util.StudentRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@StudentRequired
public class TakeExamAction extends BaseSessionAction {
    private static final long serialVersionUID = -2887663909719799155L;
    private static final Logger logger = LogManager.getLogger(TakeExamAction.class.getName());

    public static Object getIndex(final Set<? extends Object> set, final int index) {
        int result = 0;
        for (final Object entry : set) {
            if (result == index) {
                return entry;
            }
            result++;
        }
        return null;
    }

    @Autowired
    private ExamService examService;
    @Autowired
    private TestResultService testResultService;
    @Autowired
    private TestResultAnswersService testResultAnswersService;
    @Autowired
    private AnswerService answerService;
    @Getter
    @Setter
    private Long examId;
    @Getter
    @Setter
    private Long questionId;
    @Getter
    @Setter
    private Exam exam;
    @Getter
    @Setter
    private Question question;
    @Getter
    @Setter
    private Student student;
    @Getter
    @Setter
    private long endTimeMillis;
    @Getter
    @Setter
    private TestResult testResult;
    @Getter
    private int progress;

    @Getter
    @Setter
    private String[] fillInTheBlankAnswers;

    public String display() {
        exam = examService.find(examId);
        student = (Student) getUser();
        if (!exam.hasParticipant(student)) {
            logger.warn("The student {} tried to enroll to the exam {}, but he is not enlisted for this exam!",
                    student.getEmail(), exam.getName());
            throw new StudentNotEnrolledException();
        }
        if (questionId != null) {
            final Optional<Question> optionalQuestion = exam.getQuestions().stream()
                    .filter(q -> questionId.longValue() == q.getId().longValue()).findFirst();
            if (!optionalQuestion.isPresent()) {
                throw new QuestionNotFoundException();
            }
            question = optionalQuestion.get();
        } else {
            question = exam.getFirstQuestion();
        }
        final TestResult byExamAndStudent = testResultService.findByExamAndStudent(examId, student.getId());
        if (byExamAndStudent == null) {
            addActionError(getText("validation.useToken"));
            return "token";
        } else {
            testResult = byExamAndStudent;
            if (testResult.isExpired()) {
                return "expired";
            }
        }
        final LocalDateTime endTime = testResult.getStartTime().plusMinutes(exam.getExamTime().intValue());
        endTimeMillis = endTime.atOffset(ZonedDateTime.now().getOffset()).toEpochSecond();
        progress = (int) ((testResult.getSubmittedAnswers().size() * 100.0f) / exam.getQuestions().size());
        return SUCCESS;
    }

    public String saveAnswer() {
        exam = examService.find(examId);
        student = (Student) getUser();
        if (!exam.hasParticipant(student)) {
            logger.warn("The student {} tried to enroll to the exam {}, but he is not enlisted for this exam!",
                    student.getEmail(), exam.getName());
            throw new StudentNotEnrolledException();
        }
        if (questionId == null) {
            throw new QuestionNotFoundException();
        } else {
            final Optional<Question> optionalQuestion = exam.getQuestions().stream()
                    .filter(q -> questionId.longValue() == q.getId().longValue()).findFirst();
            if (!optionalQuestion.isPresent()) {
                throw new QuestionNotFoundException();
            }
            question = optionalQuestion.get();
        }
        testResult = testResultService.findByExamAndStudent(examId, student.getId());
        if (testResult.getSubmittedAnswers() == null) {
            testResult.setSubmittedAnswers(new HashMap<>());
        }
        final HashSet<Answer> answerSet = new HashSet<>();
        if (question.getType() == QuestionType.FILL_IN_THE_BLANK) {
            for (int i = 0; i < fillInTheBlankAnswers.length; i++) {
                final String blankAnwser = fillInTheBlankAnswers[i];
                if (testResult.getSubmittedAnswers().containsKey(question)) {
                    final TestResultAnswers testResultAnswers = testResult.getSubmittedAnswers().get(question);
                    final Answer answer = (Answer) getIndex(testResultAnswers.getAnswers(), i);
                    answer.setText(blankAnwser);
                    answerService.updateAnswer(answer);
                } else {
                    final Answer answer = new Answer(blankAnwser, true);
                    answerSet.add(answer);
                }
            }
            if (!testResult.getSubmittedAnswers().containsKey(question)) {
                // save answers
                final TestResultAnswers testResultAnswers = new TestResultAnswers();
                testResultAnswers.setAnswers(answerSet);
                testResultAnswersService.createTestResultAnswers(testResultAnswers);

                // add in-between object
                final Map<Question, TestResultAnswers> submittedAnswers = testResult.getSubmittedAnswers();
                submittedAnswers.put(question, testResultAnswers);
                testResult.setSubmittedAnswers(submittedAnswers);
                testResultService.updateTestResult(testResult);
            }
        }
        return SUCCESS;
    }

    public String submitExam() {
        exam = examService.find(examId);
        student = (Student) getUser();
        if (!exam.hasParticipant(student)) {
            logger.warn("The student {} tried to enroll to the exam {}, but he is not enlisted for this exam!",
                    student.getEmail(), exam.getName());
            throw new StudentNotEnrolledException();
        }
        testResult = testResultService.findByExamAndStudent(examId, student.getId());
        final LocalDateTime endTime = LocalDateTime.now();
        testResult.setEndTime(endTime);
        testResult.calculateFinalPoints();
        testResultService.updateTestResult(testResult);
        return SUCCESS;
    }
}
