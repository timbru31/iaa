package de.nordakademie.iaa_multiple_choice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Room;
import de.nordakademie.iaa_multiple_choice.service.RoomService;
import lombok.Getter;

public class ListRoomAction extends ActionSupport {

    private static final long serialVersionUID = -4999813906571079068L;

    @Getter
    private List<Room> rooms;
    private final RoomService roomService;

    @Autowired
    public ListRoomAction(final RoomService roomService) {
        this.roomService = roomService;
    }

    public String listAll() {
        rooms = roomService.listAll();
        return SUCCESS;
    }
}
