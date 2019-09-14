package ru.vtb.vtbhack.DTO

import com.fasterxml.jackson.annotation.JsonProperty
import ru.vtb.vtbhack.entity.Voting
import java.util.*

class RoomInfoDTO(
        val id: Long,
        @JsonProperty("title") val title: String,
        @JsonProperty("is_anonymous") val isAnonymous: Boolean = false,
        @JsonProperty("is_yes_no") val isYesNo: Boolean = false,
        @JsonProperty("start_time") val startTime: Date,
        @JsonProperty("end_time") val endTime: Date,
        @JsonProperty("is_auto") val isAuto: Boolean = false,
        val votings: List<Voting> = mutableListOf()
)
