package ru.vtb.vtbhack.DTO

import ru.vtb.vtbhack.entity.User

data class LoginDTO(val email: String,
                    val password: String,
                    val firstName: String? = null,
                    val secondName: String? = null,
                    val phone: String? = null
) {
    fun toUser(): User {
        return User(
                email = this.email,
                password = this.password,
                firstName = "lal",
                secondName = "lal",
                phone = phone!!
        )
    }
}
