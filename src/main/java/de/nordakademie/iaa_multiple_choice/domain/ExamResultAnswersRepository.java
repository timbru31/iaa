package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 * @author Yannick Rump repository for examResultAnswers
 */
@Repository
public class ExamResultAnswersRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void createExamResultAnswers(final ExamResultAnswers examResultAnswers) {
        entityManager.persist(examResultAnswers);
    }

    public void deleteExamResultAnswers(final ExamResultAnswers examResultAnswers) {
        entityManager.remove(examResultAnswers);
    }

    public ExamResultAnswers find(final Long id) {
        return entityManager
                .createQuery("SELECT examResultAnswers FROM ExamResultAnswers examResultAnswers WHERE id = :id",
                        ExamResultAnswers.class)
                .setParameter("id", id).getSingleResult();
    }

    public List<ExamResultAnswers> findAll() {
        return entityManager.createQuery("SELECT examResultAnswers FROM ExamResultAnswers examResultAnswers",
                ExamResultAnswers.class).getResultList();
    }

    public ExamResultAnswers updateExamResultAnswers(final ExamResultAnswers examResultAnswers) {
        return entityManager.merge(examResultAnswers);
    }
}
