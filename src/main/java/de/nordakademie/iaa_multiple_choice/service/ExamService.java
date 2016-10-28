package de.nordakademie.iaa_multiple_choice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.ExamRepository;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    @Transactional
    public void createExam(final Exam exam) {
        examRepository.createExam(exam);
    }

    @Transactional(readOnly = true)
    public Exam find(Long examId) {
        return examRepository.find(examId);
    }

    @Transactional(readOnly = true)
    public List<Exam> listAll() {
        return examRepository.findAll();
    }

    @Transactional
    public Exam updateExam(final Exam exam) {
        return examRepository.updateExam(exam);
    }
}
