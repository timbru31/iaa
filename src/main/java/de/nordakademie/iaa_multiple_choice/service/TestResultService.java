package de.nordakademie.iaa_multiple_choice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.nordakademie.iaa_multiple_choice.domain.TestResult;
import de.nordakademie.iaa_multiple_choice.domain.TestResultRepository;

/**
 * Service for test result.
 * 
 * @author Yannick Rump
 */
@Service
public class TestResultService {
    @Autowired
    private TestResultRepository testResultRepository;

    @Transactional
    public void createTestResult(final TestResult testResult) {
        testResultRepository.createTestResult(testResult);
    }

    @Transactional(readOnly = true)
    public TestResult findByExamAndStudent(final Long examId, final Long studentId) {
        return testResultRepository.find(examId, studentId);
    }

    @Transactional(readOnly = true)
    public List<TestResult> listAll() {
        return testResultRepository.findAll();
    }

    @Transactional
    public TestResult updateTestResult(final TestResult testResult) {
        return testResultRepository.updateTestResult(testResult);
    }
}
