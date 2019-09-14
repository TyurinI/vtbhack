package ru.vtb.vtbhack.controlers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.vtb.vtbhack.DTO.VotingDTO
import ru.vtb.vtbhack.persistence.VotingRepository

@RestController
class VotingController(
        @Autowired val votingRepository: VotingRepository
) {
     @GetMapping("/api/voting/{votingId}")
     fun getVoting(@PathVariable votingId:Long): VotingDTO {
         return votingRepository.findById(votingId).get().toDTO()
     }
}