package ru.vtb.vtbhack.DTO

import com.fasterxml.jackson.annotation.JsonProperty

data class GetRoomsDTO(
        @JsonProperty("user_id") val userId:Long
)