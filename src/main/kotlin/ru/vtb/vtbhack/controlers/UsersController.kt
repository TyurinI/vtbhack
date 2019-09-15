package ru.vtb.vtbhack.controlers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.vtb.vtbhack.persistence.UsersRepository

@RestController
class UsersController (
        @Autowired val usersRepository: UsersRepository
){
    @GetMapping("/api/users")
    fun getAll() = usersRepository.findAll()
}