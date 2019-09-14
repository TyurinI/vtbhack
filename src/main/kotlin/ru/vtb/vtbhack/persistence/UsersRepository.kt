package ru.vtb.vtbhack.persistence

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.vtb.vtbhack.entity.User

@Repository
interface UsersRepository : CrudRepository<User, Long>{
    fun findByEmailAndPassword(email:String, password:String): User?
}