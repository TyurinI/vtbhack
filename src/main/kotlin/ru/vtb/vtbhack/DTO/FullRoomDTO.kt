package ru.vtb.vtbhack.DTO

import ru.vtb.vtbhack.entity.Attachment
import java.util.*

class FullRoomDTO(
    val title: String,
    val description: String,
    val isAnonymous: Boolean = false,
    val isYesNo: Boolean = false,
    val startTime: Date,
    val endTime: Date,
    val isAuto: Boolean = false,
    val attachments: MutableList<Attachment> = mutableListOf(),
    val votings:MutableList<VotingDTO> = mutableListOf()
)
