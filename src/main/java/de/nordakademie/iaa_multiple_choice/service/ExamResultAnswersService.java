package de.nordakademie.iaa_multiple_choice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.nordakademie.iaa_multiple_choice.domain.ExamResultAnswers;
import de.nordakademie.iaa_multiple_choice.domain.ExamResultAnswersRepository;

/**
 * Service for answers of test.
 * 
 * @author Jens Gottwald
 */
@Service
public class ExamResultAnswersService {
    @Autowired
    private ExamResultAnswersRepository examResultAnswersRepository;

    @Transactional
    public void createExamResultAnswers(final ExamResultAnswers examResultAnswers) {
        examResultAnswersRepository.createExamResultAnswers(examResultAnswers);
    }
}
