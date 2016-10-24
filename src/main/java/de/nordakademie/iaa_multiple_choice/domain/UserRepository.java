package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void createUser(final User user) {
        entityManager.persist(user);
    }

    public User find(final String userNaturalId) {
        return entityManager.createQuery("SELECT user FROM User user", User.class)
                .setParameter("userNaturalId", userNaturalId).getSingleResult();

    }

    public List<User> findAll() {
        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }

    public User updateUser(final User updatedUser) {
        return entityManager.merge(updatedUser);
    }
}
