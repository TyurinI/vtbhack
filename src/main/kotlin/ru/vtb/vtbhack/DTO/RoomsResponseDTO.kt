package ru.vtb.vtbhack.DTO

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class RoomsResponseDTO(
        @JsonProperty("title") val title: String,
        @JsonProperty("is_actual") val isActual: Boolean,
        @JsonProperty("start_time") val startTime: Date,
        @JsonProperty("end_time") val endTime: Date,
        @JsonProperty("user_count") val userCount: Int,
        @JsonProperty("file_count") val fileCount: Int
        )