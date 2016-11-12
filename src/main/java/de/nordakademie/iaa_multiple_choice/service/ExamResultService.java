package de.nordakademie.iaa_multiple_choice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.nordakademie.iaa_multiple_choice.domain.ExamResult;
import de.nordakademie.iaa_multiple_choice.domain.ExamResultRepository;

/**
 * @author Yannick Rump service for test result
 */
@Service
public class ExamResultService {
    @Autowired
    private ExamResultRepository examResultRepository;

    @Transactional
    public void createExamResult(final ExamResult examResult) {
        examResultRepository.createExamResult(examResult);
    }

    @Transactional(readOnly = true)
    public ExamResult findByExamAndStudent(final Long examId, final Long studentId) {
        return examResultRepository.find(examId, studentId);
    }

    @Transactional(readOnly = true)
    public List<ExamResult> listAll() {
        return examResultRepository.findAll();
    }

    @Transactional
    public ExamResult updateExamResult(final ExamResult examResult) {
        return examResultRepository.updateExamResult(examResult);
    }
}
