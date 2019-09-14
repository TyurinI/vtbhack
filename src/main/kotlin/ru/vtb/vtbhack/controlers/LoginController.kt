package ru.vtb.vtbhack.controlers

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.vtb.vtbhack.DTO.GetRoomsDTO
import ru.vtb.vtbhack.DTO.LoginDTO
import ru.vtb.vtbhack.persistence.UsersRepository
private val logger = KotlinLogging.logger {}
@RestController("/api")
class LoginController (@Autowired val usersRepository: UsersRepository){

    @RequestMapping(method = [RequestMethod.POST], name = "/login")
    fun login(@RequestBody loginDTO: LoginDTO): GetRoomsDTO {
        val newUser = loginDTO.toUser()
        usersRepository.save(newUser)
        logger.info {newUser}
        return GetRoomsDTO(newUser.id!!)
    }

}