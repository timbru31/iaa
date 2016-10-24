package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class QuestionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void createQuestion(final Question question) {
        entityManager.persist(question);
    }

    public Question find(final Long id) {
        return entityManager.createQuery("SELECT question FROM Question question", Question.class)
                .setParameter("Id", id).getSingleResult();

    }

    public List<Question> findAll() {
        return entityManager.createQuery("SELECT question FROM Answer question", Question.class).getResultList();
    }

    public Question updateQuestion(final Question updatedQuestion) {
        return entityManager.merge(updatedQuestion);
    }
}
