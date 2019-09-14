package ru.vtb.vtbhack.controlers

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.vtb.vtbhack.DTO.GetRoomsDTO
import ru.vtb.vtbhack.DTO.LoginDTO
import ru.vtb.vtbhack.persistence.UsersRepository
private val logger = KotlinLogging.logger {}
@RestController
class LoginController (@Autowired val usersRepository: UsersRepository){

    @PostMapping("/api/login")
    fun login(@RequestBody loginDTO: LoginDTO): GetRoomsDTO {
        val newUser = loginDTO.toUser()
        usersRepository.save(newUser)
        logger.info {newUser}
        return GetRoomsDTO(newUser.id!!)
    }

}