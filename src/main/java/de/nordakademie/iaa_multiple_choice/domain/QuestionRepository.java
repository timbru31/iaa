package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class QuestionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void createQuestion(final Answer question) {
        entityManager.persist(question);
    }

    public Question find(final long id) {
        return entityManager.createQuery("SELECT question FROM Question question", Question.class)
                .setParameter("Id", id).getSingleResult();

    }

    public List<Question> findAll() {
        return entityManager.createQuery("SELECT question FROM Answer question", Question.class).getResultList();
    }
}
