package de.nordakademie.iaa_multiple_choice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.nordakademie.iaa_multiple_choice.domain.Room;
import de.nordakademie.iaa_multiple_choice.domain.RoomRepository;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(final RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional
    public void saveRoom(final Room room) {
        roomRepository.createRoom(room);
    }

    @Transactional(readOnly = true)
    public List<Room> listAll() {
        return roomRepository.findAll();
    }
}
