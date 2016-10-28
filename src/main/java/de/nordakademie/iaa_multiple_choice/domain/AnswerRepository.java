package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class AnswerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void createAnswer(final Answer answer) {
        entityManager.persist(answer);
    }

    public void deleteAnswer(final Long answerId) {
        entityManager.createQuery("DELETE anser FROM Answer answer WHERE answer_id = :answer_id", Answer.class)
                .setParameter("answer_id", answerId).executeUpdate();
    }

    public Answer find(final Long answerId) {
        return entityManager.createQuery("SELECT answer FROM Answer answer WHERE answer_id = :answer_id", Answer.class)
                .setParameter("answer_id", answerId).getSingleResult();
    }

    public List<Answer> findAll() {
        return entityManager.createQuery("SELECT answer FROM Answer answer", Answer.class).getResultList();
    }

    public final Answer updateAnswer(final Answer updatedAnswer) {
        return entityManager.merge(updatedAnswer);
    }
}
