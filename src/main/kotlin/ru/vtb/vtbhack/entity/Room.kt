package ru.vtb.vtbhack.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import ru.vtb.vtbhack.DTO.RoomInfoDTO
import java.util.*
import javax.persistence.*


@Entity
@Table
data class Room(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JsonIgnore
        var usrs: MutableList<User> = mutableListOf(),

        @OneToMany(mappedBy = "room")
        var votings: MutableList<Voting> = mutableListOf(),

        @JsonProperty("title") val title: String,
        @JsonProperty("is_anonymous") val isAnonymous: Boolean = false,
        @JsonProperty("is_yes_no") val isYesNo: Boolean = false,
        @JsonProperty("start_time") val startTime: Date,
        @JsonProperty("end_time") val endTime: Date,
        @JsonProperty("is_auto") val isAuto: Boolean = false
)
