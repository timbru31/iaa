package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void createExam(final Exam exam) {
        entityManager.persist(exam);
    }

    public Exam find(final Long id) {
        return entityManager.createQuery("SELECT exam FROM Exam exam", Exam.class).setParameter("Id", id)
                .getSingleResult();

    }

    public List<Exam> findAll() {
        return entityManager.createQuery("SELECT question FROM Exam exam", Exam.class).getResultList();
    }

    public final Exam updateExam(final Exam updatedExam) {
        return entityManager.merge(updatedExam);
    }

}
