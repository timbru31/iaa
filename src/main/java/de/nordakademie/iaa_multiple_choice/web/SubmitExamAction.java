package de.nordakademie.iaa_multiple_choice.web;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import de.nordakademie.iaa_multiple_choice.domain.Answer;
import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.Question;
import de.nordakademie.iaa_multiple_choice.domain.Student;
import de.nordakademie.iaa_multiple_choice.domain.TestResult;
import de.nordakademie.iaa_multiple_choice.service.ExamService;
import de.nordakademie.iaa_multiple_choice.service.TestResultService;
import de.nordakademie.iaa_multiple_choice.web.util.LoginRequired;
import de.nordakademie.iaa_multiple_choice.web.util.StudentRequired;
import lombok.Getter;
import lombok.Setter;

@LoginRequired
@StudentRequired
public class SubmitExamAction extends BaseSessionAction {

    private static final long serialVersionUID = 5264098343739748565L;
    @Autowired
    private ExamService examService;
    @Autowired
    private TestResultService testResultService;
    @Getter
    @Setter
    private Exam exam;
    @Getter
    @Setter
    private Student student;
    @Getter
    @Setter
    private Map<Question, Set<Answer>> submittedAnswers;
    @Getter
    @Setter
    private TestResult testResult;
    @Getter
    @Setter
    private long examId;
    @Getter
    @Setter
    private LocalDateTime startTime;
    @Getter
    @Setter
    private LocalDateTime endTime;

    private String submitExam() {
        exam = examService.find(examId);
        startTime = testResult.getStartTime();
        endTime = testResult.getStartTime().plusMinutes(exam.getExamTime().intValue());
        for (final Map.Entry<Question, Set<Answer>> answer : submittedAnswers.entrySet()) {
            final Question key = answer.getKey();
            for (final Answer value : answer.getValue()) {
                // TODO: catching the right answers
            }
        }
        testResult = testResultService.findByExamAndStudent(examId, student.getId());
        testResult.setStartTime(startTime);
        testResult.setEndTime(endTime);
        testResult.setPoints(testResult.calculateFinalPoints(submittedAnswers));
        testResult.setPassed(testResult.isPassed());
        testResultService.createTestResult(testResult);
        return SUCCESS;
    }

}
