package ru.vtb.vtbhack.controlers

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import ru.vtb.vtbhack.DTO.ConfirmDTO
import ru.vtb.vtbhack.DTO.CredentialsDTO
import ru.vtb.vtbhack.DTO.LoginDTO
import ru.vtb.vtbhack.persistence.UsersRepository
import java.util.*
import javax.xml.ws.Response


private val logger = KotlinLogging.logger {}

@RestController
class LoginController(@Autowired val usersRepository: UsersRepository) {

    val restTemplate = RestTemplate()
    val registgrationUserCahce = hashMapOf<String, Int>()

    @PostMapping("/api/login")
    fun login(@RequestBody loginDTO: LoginDTO): CredentialsDTO? {
        val oldUser = usersRepository.findByEmailAndPassword(loginDTO.email, loginDTO.password)
        return if (oldUser != null) {
            CredentialsDTO(oldUser.id!!, oldUser.uuid,
                    oldUser.email, oldUser.firstName, oldUser.secondName)
        } else {
            return null
        }

    }

    @PostMapping("/api/register")
    fun register(@RequestBody loginDTO: LoginDTO) {
        loginViaPhone(loginDTO.phone)
    }

    @PostMapping("/api/confirm")
    fun confirmRegistration(@RequestBody confirmDTO: ConfirmDTO): ResponseEntity<*> {
        if (confirmDTO.code == registgrationUserCahce[confirmDTO.phone]) {
            registgrationUserCahce.remove(confirmDTO.phone)
            val newUser = usersRepository.save(confirmDTO.toUser())
            return ResponseEntity.ok()
                    .body(
                            CredentialsDTO(
                                    userId = newUser.id!!,
                                    token = newUser.uuid,
                                    email = newUser.email,
                                    firstName = newUser.firstName,
                                    secondName = newUser.secondName
                            )
                    )
        } else {
            return ResponseEntity.badRequest().body("Попробуйте еще раз")
        }
    }

    fun loginViaPhone(phone: String?) {
        val code = Random().nextInt(9999 - 1000) + 1000
        val answer: String? = restTemplate.getForObject(
                "http://smsc.ru/sys/send.php?" +
                        "login=elin&psw=20002000&phones=$phone&mes=$code", String::class)
        registgrationUserCahce[phone!!] = code
    }

}