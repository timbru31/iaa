package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 * @author Yannick Rump repository for testResultAnswers
 */
@Repository
public class TestResultAnswersRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void createTestResultAnswers(final TestResultAnswers testResultAnswers) {
        entityManager.persist(testResultAnswers);
    }

    public void deleteTestResultAnswers(final TestResultAnswers testResultAnswers) {
        entityManager.remove(testResultAnswers);
    }

    public TestResultAnswers find(final Long id) {
        return entityManager
                .createQuery("SELECT testResultAnswers FROM TestResultAnswers testResultAnswers WHERE id = :id",
                        TestResultAnswers.class)
                .setParameter("id", id).getSingleResult();
    }

    public List<TestResultAnswers> findAll() {
        return entityManager.createQuery("SELECT testResultAnswers FROM TestResultAnswers testResultAnswers",
                TestResultAnswers.class).getResultList();
    }

    public TestResultAnswers updateTestResultAnswers(final TestResultAnswers testResultAnswers) {
        return entityManager.merge(testResultAnswers);
    }
}
