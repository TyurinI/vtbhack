package ru.vtb.vtbhack.service

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.vtb.vtbhack.DTO.FullRoomDTO
import ru.vtb.vtbhack.DTO.RoomsResponseDTO
import ru.vtb.vtbhack.entity.Room
import ru.vtb.vtbhack.exceptions.PermissionDeniedException
import ru.vtb.vtbhack.persistence.RoomRepository
import ru.vtb.vtbhack.persistence.UsersRepository

private val logger = KotlinLogging.logger {}

@Service
class RoomService(
        @Autowired private val roomRepository: RoomRepository,
        @Autowired private val usersRepository: UsersRepository) {

    fun getRoomsRelatedToUser(userId: Long): List<RoomsResponseDTO> {
        val user = usersRepository.findById(userId).get()
        return user.toShortRoomsResponse()
    }

    fun getRoomInfo(roomId: Long): Room {
        return roomRepository.findById(roomId).get()
    }

    fun checkUserPermissions(userId: Long, token: String) {
        val userUuid = usersRepository.findById(userId).get().uuid
        logger.info { "$userUuid  $token" }
        if ("Token $userUuid" != token)
            throw PermissionDeniedException()
    }

    fun save(room:Room){
        roomRepository.save(room)
    }
}