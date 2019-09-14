package ru.vtb.vtbhack.DTO

import com.fasterxml.jackson.annotation.JsonProperty

data class RoomsResponseDTO(
        @JsonProperty("title") val title: String,
        @JsonProperty("is_actual") val isActual: Boolean
)