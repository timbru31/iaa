package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void createUser(final User user) {
        entityManager.persist(user);
    }

    public User find(final String userNaturalId) {
        try {
            return entityManager.createQuery("SELECT user FROM User user WHERE email = :email", User.class)
                    .setParameter("email", userNaturalId).getSingleResult();
        } catch (final NoResultException e) {
            return null;
        }
    }

    public List<User> findAll() {
        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }

    public User updateUser(final User updatedUser) {
        return entityManager.merge(updatedUser);
    }
}
