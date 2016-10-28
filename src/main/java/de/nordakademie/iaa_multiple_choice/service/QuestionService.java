package de.nordakademie.iaa_multiple_choice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.nordakademie.iaa_multiple_choice.domain.Question;
import de.nordakademie.iaa_multiple_choice.domain.QuestionRepository;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Transactional
    public void createQuestion(final Question question) {
        questionRepository.createQuestion(question);
    }

    @Transactional
    public void deleteQuestion(final Long questionId) {
        questionRepository.deleteQuestion(questionId);
    }

    @Transactional
    public Question find(Long questionId) {
        return questionRepository.find(questionId);
    }

    @Transactional(readOnly = true)
    public List<Question> listAll() {
        return questionRepository.findAll();
    }

    @Transactional
    public Question updateQuestion(final Question question) {
        return questionRepository.updateQuestion(question);
    }

}
