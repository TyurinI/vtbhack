package ru.vtb.vtbhack.persistence

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import ru.vtb.vtbhack.entity.Voting
import javax.transaction.Transactional

interface VotingRepository: CrudRepository<Voting, Long>{
    @Query("select * from Voting v where v.room_id = :roomId", nativeQuery = true)
    fun getvot(@Param("roomId") roomId:Long):List<Voting>
}