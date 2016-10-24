package de.nordakademie.iaa_multiple_choice.web.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomRestController {

    // @Autowired
    // private RoomService roomService;
    //
    // @GetMapping
    // public ResponseEntity<List<Room>> getAllRooms() {
    // return new ResponseEntity<>(roomService.listAll(), HttpStatus.OK);
    // }
}
