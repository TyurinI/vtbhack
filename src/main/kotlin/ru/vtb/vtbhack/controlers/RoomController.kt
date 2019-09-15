package ru.vtb.vtbhack.controlers

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.PermissionDeniedDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.vtb.vtbhack.DTO.FullRoomDTO
import ru.vtb.vtbhack.DTO.GetRoomsDTO
import ru.vtb.vtbhack.DTO.RoomsResponseDTO
import ru.vtb.vtbhack.entity.Room
import ru.vtb.vtbhack.entity.User
import ru.vtb.vtbhack.exceptions.PermissionDeniedException
import ru.vtb.vtbhack.service.RoomService

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping
class RoomController(
        @Autowired val roomService: RoomService
) {
    @PostMapping("/api/rooms")
    fun getRoomsRelatedToUser(@RequestHeader("Authorization") token: String,
                              @RequestBody getRoomsDTO: GetRoomsDTO): ResponseEntity<*> {
        logger.info { token }
        logger.info { getRoomsDTO }
        return try {
            roomService.checkUserPermissions(getRoomsDTO.userId, token)
            val roomsRelatedToUser = roomService.getRoomsRelatedToUser(getRoomsDTO.userId)
            ResponseEntity.ok()
                    .body(roomsRelatedToUser)
        } catch (e: PermissionDeniedException) {
            ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.message)

        }
    }


    @GetMapping("/api/room/{roomId}")
    fun getRoom(@PathVariable roomId: Long): Room {
        return roomService.getRoomInfo(roomId)
    }
}