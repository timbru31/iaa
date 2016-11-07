package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class TestResultRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void createTestResult(final TestResult question) {
        entityManager.persist(question);
    }

    public void deleteTestResult(final Long id) {
        entityManager.createQuery("DELETE testResult FROM TestResult testResult WHERE id = :id", TestResult.class)
                .setParameter("id", id).executeUpdate();
    }

    public TestResult find(final Long id) {
        return entityManager
                .createQuery("SELECT testResult FROM TestResult testResult WHERE id = :id", TestResult.class)
                .setParameter("id", id).getSingleResult();
    }

    public TestResult find(final Long examId, final Long studentId) {
        try {
            return entityManager
                    .createQuery(
                            "SELECT testResult FROM TestResult testResult WHERE exam_exam_id = :examId AND student_id = :studentId",
                            TestResult.class)
                    .setParameter("examId", examId).setParameter("studentId", studentId).getSingleResult();
        } catch (final NoResultException e) {
            return null;
        }
    }

    public List<TestResult> findAll() {
        return entityManager.createQuery("SELECT testResult FROM TestResult testResult", TestResult.class)
                .getResultList();
    }

    public TestResult updateTestResult(final TestResult updatedTestResult) {
        return entityManager.merge(updatedTestResult);
    }
}
