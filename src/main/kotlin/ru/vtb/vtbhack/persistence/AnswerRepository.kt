package ru.vtb.vtbhack.persistence

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.vtb.vtbhack.entity.Answer
import javax.transaction.Transactional

@Repository
interface AnswerRepository : CrudRepository<Answer, Long>