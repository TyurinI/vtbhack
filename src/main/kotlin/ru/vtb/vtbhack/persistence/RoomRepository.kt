package ru.vtb.vtbhack.persistence

import org.springframework.data.repository.CrudRepository
import ru.vtb.vtbhack.entity.Room

interface RoomRepository: CrudRepository<Room, Long>