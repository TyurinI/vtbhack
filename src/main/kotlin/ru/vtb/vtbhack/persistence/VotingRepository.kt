package ru.vtb.vtbhack.persistence

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.vtb.vtbhack.entity.Voting

interface VotingRepository: CrudRepository<Voting, Long>{
    fun findAllByRoomId(roomId:Long):List<Voting>
}