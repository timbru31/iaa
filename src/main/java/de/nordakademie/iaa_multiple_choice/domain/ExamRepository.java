package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import de.nordakademie.iaa_multiple_choice.domain.exceptions.ExamNotFoundException;

@Repository
public class ExamRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void createExam(final Exam exam) {
        entityManager.persist(exam);
    }

    public Exam find(final Long examId) {
        try {
            return entityManager.createQuery("SELECT exam FROM Exam exam WHERE exam_id = :exam_id", Exam.class)
                    .setParameter("exam_id", examId).getSingleResult();
        } catch (final NoResultException e) {
            throw new ExamNotFoundException();
        }
    }

    public List<Exam> findAll() {
        return entityManager.createQuery("SELECT exam FROM Exam exam", Exam.class).getResultList();
    }

    /* Muss das in Question oder Exam? */
    public List<Question> findQuestions(Exam exam) {
        return entityManager
                .createQuery("SELECT question FROM Question question WHERE examId = :examId", Question.class)
                .setParameter("examId", exam.getId()).getResultList();
    }

    public final Exam updateExam(final Exam updatedExam) {
        return entityManager.merge(updatedExam);
    }

}
