package de.nordakademie.iaa_multiple_choice.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class RoomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void createRoom(final Room room) {
        entityManager.persist(room);
    }

    public List<Room> findAll() {
        return entityManager.createQuery("SELECT room FROM Room room", Room.class).getResultList();
    }

    public Room find(final String roomNaturalId) {
        return entityManager
                .createQuery("SELECT room FROM Room room WHERE CONCAT(room.building, room.number) = :roomNaturalId",
                        Room.class)
                .setParameter("roomNaturalId", roomNaturalId).getSingleResult();
    }
}
