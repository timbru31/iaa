package de.nordakademie.iaa_multiple_choice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.nordakademie.iaa_multiple_choice.domain.Exam;
import de.nordakademie.iaa_multiple_choice.domain.ExamRepository;
import de.nordakademie.iaa_multiple_choice.domain.Question;
import de.nordakademie.iaa_multiple_choice.domain.QuestionRepository;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public ExamService(final ExamRepository examRepository, final QuestionRepository questionRepository) {
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional
    public void createExam(final Exam exam) {
        examRepository.createExam(exam);
    }

    @Transactional(readOnly = true)
    public Exam find(Long examId) {
        return examRepository.find(examId);
    }

    @Transactional
    public List<Question> getQuestions(final Exam exam) {
        return questionRepository.findQuestions(exam);
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
