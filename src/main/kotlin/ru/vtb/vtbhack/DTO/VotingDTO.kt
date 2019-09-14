package ru.vtb.vtbhack.DTO

import ru.vtb.vtbhack.entity.Answer

data class VotingDTO(
        val id: Long?,
        val title: String,
        val type: String,
        val answers: List<Answer>
)