package ru.vtb.vtbhack.controlers

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import org.springframework.web.client.getForObject
import ru.vtb.vtbhack.DTO.CredentialsDTO
import ru.vtb.vtbhack.DTO.LoginDTO
import ru.vtb.vtbhack.persistence.UsersRepository
import java.util.*


private val logger = KotlinLogging.logger {}

@RestController
class LoginController(@Autowired val usersRepository: UsersRepository) {
    val restTemplate = RestTemplate()
    @PostMapping("/api/login")
    fun login(@RequestBody loginDTO: LoginDTO): CredentialsDTO {
        val oldUser = usersRepository.findByEmailAndPassword(loginDTO.email, loginDTO.password)
        return if (oldUser != null) {
            CredentialsDTO(oldUser.id!!, oldUser.uuid,
                    oldUser.email, oldUser.firstName, oldUser.secondName)
        } else {
            var newUser = loginDTO.toUser()
            newUser = usersRepository.save(newUser)
            CredentialsDTO(newUser.id!!, newUser.uuid, email = newUser.email)
        }

    }

    fun loginViaPhone(phone: String) {
        val random = Random().nextInt(9999 - 1000) + 1000
        val answer: String? = restTemplate.getForObject(
                "http://smsc.ru/sys/send.php?" +
                        "login=elin&psw=20002000&phones=$phone&mes=$random", String::class)

    }

}