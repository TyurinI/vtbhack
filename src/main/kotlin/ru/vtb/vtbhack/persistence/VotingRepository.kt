package ru.vtb.vtbhack.persistence

import org.springframework.data.repository.CrudRepository
import ru.vtb.vtbhack.entity.Voting

interface VotingRepository: CrudRepository<Voting, Long>