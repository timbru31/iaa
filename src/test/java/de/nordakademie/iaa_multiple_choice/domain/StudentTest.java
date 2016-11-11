package de.nordakademie.iaa_multiple_choice.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for student utility methods.
 *
 * @author Tim Brust
 *
 */
public class StudentTest {
    private Exam exam;
    private Student student;

    @Before
    public void setUp() {
        student = new Student();
        exam = new Exam();
        exam.setId(1L);
        exam.setName("Test");
        exam.setCreditPoints(CreditPointType.HALF);
        exam.setEvaluationMethod(WrongAnswerEvaluationMethod.NO_SUBTRACTION);
        exam.setMinPoints(50);
        exam.setQuestions(new HashSet<>());
        exam.setTestResults(new HashSet<>());
        exam.setExamTime(60);
        exam.setEndDate(LocalDate.now().plusDays(20L));
        exam.setStartDate(LocalDate.now());
        student.setRegisteredExams(new HashSet<Exam>());
        exam.setTokenList(new HashMap<>());
        student.addExam(exam);
        student.setTestResults(new HashSet<>());
    }

    /**
     * Test that student has exams to take. Tests for finished and new exams.
     */
    @Test
    public void testCanTakeExams() {
        student.removeExam(exam);
        assertFalse("Expected that student can not take exams", student.canTakeExams());
        student.addExam(exam);
        assertTrue("Expected that student can take exams", student.canTakeExams());

        final TestResult testResult = new TestResult();
        testResult.setStartTime(LocalDateTime.now());
        testResult.setExam(exam);
        student.addTestResult(testResult);
        exam.addTestResult(testResult);
        testResult.setEndTime(LocalDateTime.now());
        assertFalse("Expected that student can not take exams", student.canTakeExams());
    }

    /**
     * Test that no duplicate exams can be added.
     */
    @Test
    public void testDuplicateExams() {
        student.addExam(exam);
        assertEquals("Expected to have one exam", 1, student.getRegisteredExams().size());
    }

    /**
     * Test that exam has been correctly finished.
     */
    @Test
    public void testHasFinishedExam() {
        final TestResult testResult = new TestResult();
        testResult.setStartTime(LocalDateTime.now());
        testResult.setExam(exam);
        student.addTestResult(testResult);
        exam.addTestResult(testResult);
        assertFalse("Expected that student has not finished exam", student.hasFinishedExam(exam));
        testResult.setEndTime(LocalDateTime.now());
        assertTrue("Expected that student has finished exam", student.hasFinishedExam(exam));
    }

    /**
     * Test that student has taken exam.
     */
    @Test
    public void testHasTakenExam() {
        student.setTestResults(new HashSet<>());
        final TestResult testResult = new TestResult();
        testResult.setStartTime(LocalDateTime.now());
        assertFalse("Expected that student has not taken exam", student.hasTakenExam(exam));
        testResult.setExam(exam);
        student.addTestResult(testResult);
        exam.addTestResult(testResult);
        student.addExam(exam);
        testResult.setEndTime(LocalDateTime.now());
        assertTrue("Expected that student has finished exam", student.hasTakenExam(exam));
    }
}
