package de.nordakademie.iaa_multiple_choice.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.nordakademie.iaa_multiple_choice.domain.Answer;
import de.nordakademie.iaa_multiple_choice.domain.AnswerRepository;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Transactional
    public void createAnswer(final Answer answer) {
        answerRepository.createAnswer(answer);
    }

    @Transactional
    public void deleteAnswer(final Long answerId) {
        final Answer answer = find(answerId);
        if (answer == null) {
            throw new EntityNotFoundException();
        }
        answerRepository.deleteAnswer(answer);
    }

    @Transactional
    public Answer find(final Long answerId) {
        return answerRepository.find(answerId);
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
