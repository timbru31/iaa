package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import de.nordakademie.iaa_multiple_choice.domain.exceptions.QuestionNotFoundException;

/**
 * Question repository.
 * 
 * @author Hannes Peterson
 */
@Repository
public class QuestionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void createQuestion(final Question question) {
        entityManager.persist(question);
    }

    public void deleteQuestion(final Question question) {
        entityManager.refresh(question);
    }

    public Question find(final Long id) {
        try {
            return entityManager.createQuery("SELECT question FROM Question question WHERE id = :id", Question.class)
                    .setParameter("id", id).getSingleResult();
        } catch (final NoResultException e) {
            throw new QuestionNotFoundException();
        }
    }

    public List<Question> findAll() {
        return entityManager.createQuery("SELECT question FROM Question question", Question.class).getResultList();
    }

    public Question updateQuestion(final Question updatedQuestion) {
        return entityManager.merge(updatedQuestion);
    }
}
