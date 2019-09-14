package ru.vtb.vtbhack.DTO

import ru.vtb.vtbhack.entity.User

data class LoginDTO(val email: String, val password: String) {
    fun toUser(): User {
        return User(
                email = this.email,
                password = this.password
        )
    }
}
