package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 * @author Hannes Peterson repository for test results
 */
@Repository
public class ExamResultRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void createExamResult(final ExamResult examResult) {
        entityManager.persist(examResult);
    }

    public void deleteExamResult(final ExamResult examResult) {
        entityManager.remove(examResult);
    }

    public ExamResult find(final Long id) {
        return entityManager
                .createQuery("SELECT examResult FROM ExamResult examResult WHERE id = :id", ExamResult.class)
                .setParameter("id", id).getSingleResult();
    }

    public ExamResult find(final Long examId, final Long studentId) {
        try {
            return entityManager
                    .createQuery(
                            "SELECT examResult FROM ExamResult examResult WHERE exam_id = :examId AND student_id = :studentId",
                            ExamResult.class)
                    .setParameter("examId", examId).setParameter("studentId", studentId).getSingleResult();
        } catch (final NoResultException e) {
            return null;
        }
    }

    public List<ExamResult> findAll() {
        return entityManager.createQuery("SELECT examResult FROM ExamResult examResult", ExamResult.class)
                .getResultList();
    }

    public ExamResult updateExamResult(final ExamResult updatedExamResult) {
        return entityManager.merge(updatedExamResult);
    }
}
