package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void createCourse(final Course course) {
        entityManager.persist(course);
    }

    public List<Course> findAll() {
        return entityManager.createQuery("SELECT course FROM Course course", Course.class).getResultList();
    }

    public Course find(final String courseNaturalId) {
        return entityManager.createQuery(
                "SELECT course FROM Course course WHERE CONCAT(course.fieldOfStudy, course.number) = :courseNaturalId",
                Course.class).setParameter("courseNaturalId", courseNaturalId).getSingleResult();
    }
}
