package ru.vtb.vtbhack.controlers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.vtb.vtbhack.DTO.GetRoomsDTO
import ru.vtb.vtbhack.DTO.RoomsResponseDTO
import ru.vtb.vtbhack.entity.Room
import ru.vtb.vtbhack.service.RoomService

@RestController
@RequestMapping("/api")
class RoomController(
        @Autowired val roomService: RoomService
){
    @PostMapping("/rooms")
    fun getRoomsRelatedToUser(@RequestBody getRoomsDTO: GetRoomsDTO): List<RoomsResponseDTO> {
        return roomService.getRoomsRelatedToUser(getRoomsDTO.userId)
    }

    @GetMapping("/room/{roomId}")
    fun getRoom(@PathVariable roomId:Long): Room {
        return roomService.getRoomInfo(roomId)
    }
}