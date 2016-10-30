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

    public List<Answer> find(final Long id) {
        return entityManager.createQuery("SELECT answer FROM Answer answer WHERE id = :id", Answer.class)
                .setParameter("id", id).getResultList();
    }
    //
    // public Answer find(final Long id) {
    // return entityManager.createQuery("SELECT answer FROM Answer answer WHERE answer_id = :answer_id", Answer.class)
    // .setParameter("answer_id", id).getSingleResult();
    // }

    public List<Answer> findAll() {
        return entityManager.createQuery("SELECT answer FROM Answer answer", Answer.class).getResultList();
    }

    public List<Answer> findRightAnswers(final Long id) {
        return entityManager
                .createQuery("SELECT answer FROM Answer answer WHERE id = :id AND rightAnswer = true", Answer.class)
                .setParameter("questionId", id).getResultList();
    }

    public final Answer updateAnswer(final Answer updatedAnswer) {
        return entityManager.merge(updatedAnswer);
    }
}
