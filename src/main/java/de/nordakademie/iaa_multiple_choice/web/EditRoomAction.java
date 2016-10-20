package de.nordakademie.iaa_multiple_choice.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.nordakademie.iaa_multiple_choice.domain.Room;
import de.nordakademie.iaa_multiple_choice.service.RoomService;
import lombok.Getter;
import lombok.Setter;

public class EditRoomAction extends ActionSupport {

    private static final long serialVersionUID = -1407379272825435187L;

    @Getter
    @Setter
    private Room room;
    private final RoomService roomService;

    @Autowired
    public EditRoomAction(final RoomService roomService) {
        this.roomService = roomService;
    }

    public String saveRoom() {
        roomService.saveRoom(room);
        return SUCCESS;
    }
}
