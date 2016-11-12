package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 * @author Jens Gottwald repository for user
 */
@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void createUser(final User user) {
        entityManager.persist(user);
    }

    public List<User> findAll() {
        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }

    public User findByMail(final String userNaturalId) {
        try {
            return entityManager.createQuery("SELECT user FROM User user WHERE email = :email", User.class)
                    .setParameter("email", userNaturalId).getSingleResult();
        } catch (final NoResultException e) {
            return null;
        }
    }

    public Student findByStudentNumber(final Integer studentNumber) {
        try {
            return entityManager.createQuery("SELECT student FROM Student student WHERE studentNumber = :studentNumber",
                    Student.class).setParameter("studentNumber", studentNumber).getSingleResult();
        } catch (final NoResultException e) {
            return null;
        }
    }

    public User findByToken(final String activationToken) {
        try {
            return entityManager
                    .createQuery("SELECT user FROM User user WHERE activationToken = :activationToken", User.class)
                    .setParameter("activationToken", activationToken).getSingleResult();
        } catch (final NoResultException e) {
            return null;
        }
    }

    public User updateUser(final User updatedUser) {
        return entityManager.merge(updatedUser);
    }
}
