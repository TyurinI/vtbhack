package ru.vtb.vtbhack.DTO

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class CredentialsDTO(
        @JsonProperty("user_id") val userId: Long,
        @JsonProperty("access_token") val token: UUID,
        @JsonProperty("email") val email: String,
        @JsonProperty("first_name")val firstName:String?=null,
        @JsonProperty("second_name")val secondName:String?=null
)