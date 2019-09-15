package ru.vtb.vtbhack.DTO

import com.fasterxml.jackson.annotation.JsonProperty
import ru.vtb.vtbhack.entity.User

data class ConfirmDTO(
        val phone: String,
        val code: Int,
        val email: String,
        val password: String,
        @JsonProperty("first_name") val firstName: String? = null,
        @JsonProperty("second_name") val secondName: String? = null
) {
    fun toUser() = User(
                firstName = this.firstName!!,
                secondName = this.secondName!!,
                email = this.email,
                password = this.password,
                phone = this.phone
        )
}
