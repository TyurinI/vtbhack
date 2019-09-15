package ru.vtb.vtbhack.persistence

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.vtb.vtbhack.entity.Answer
import java.util.*

@Repository
interface AnswerRepository : CrudRepository<Answer, Long>{
    @Query("UPDATE Answer a set a.voted = a.voted + 1 WHERE a.id = :id")
    fun increment(id:Long): Optional<Answer>

    @Query("UPDATE Answer a set a.voted = a.voted - 1 WHERE a.id = :id")
    fun decrement(id:Long)
}