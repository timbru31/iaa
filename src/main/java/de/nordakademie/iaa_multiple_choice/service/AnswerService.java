package de.nordakademie.iaa_multiple_choice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.nordakademie.iaa_multiple_choice.domain.Answer;
import de.nordakademie.iaa_multiple_choice.domain.AnswerRepository;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(final AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Transactional
    public void createAnswer(final Answer answer) {
        answerRepository.createAnswer(answer);
    }

    @Transactional
    public void deleteAnswer(final Long id) {
        answerRepository.deleteAnswer(id);
    }

    @Transactional(readOnly = true)
    public List<Answer> listAll() {
        return answerRepository.findAll();
    }

    @Transactional
    public Answer updateAnswer(final Answer answer) {
        return answerRepository.updateAnswer(answer);
    }
}
