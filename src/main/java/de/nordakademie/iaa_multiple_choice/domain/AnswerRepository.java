package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 * @author Jens Gottwald repository for answers
 */
@Repository
public class AnswerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void createAnswer(final Answer answer) {
        entityManager.persist(answer);
    }

    public void deleteAnswer(final Answer answer) {
        entityManager.remove(answer);
    }

    public Answer find(final Long id) {
        return entityManager.createQuery("SELECT answer FROM Answer answer WHERE id = :id", Answer.class)
                .setParameter("id", id).getSingleResult();
    }

    public List<Answer> findAll() {
        return entityManager.createQuery("SELECT answer FROM Answer answer", Answer.class).getResultList();
    }

    public final Answer updateAnswer(final Answer updatedAnswer) {
        return entityManager.merge(updatedAnswer);
    }
}
