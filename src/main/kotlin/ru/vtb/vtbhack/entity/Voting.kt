package ru.vtb.vtbhack.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import ru.vtb.vtbhack.DTO.VotingDTO
import javax.persistence.*

@Entity
@Table
data class Voting(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val title: String,
        val type: String,

        @ManyToOne
        @JsonIgnore
        val room: Room,

        @OneToMany(mappedBy = "voting")
        @JsonIgnore
        val answers: List<Answer>
) {
    fun toDTO(): VotingDTO {
        return VotingDTO(
                id = this.id,
                title = this.title,
                type = this.type,
                answers = this.answers
        )
    }
}

@Entity
@Table
class Answer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val value: String,

        @ManyToOne
        @JsonIgnore
        val voting: Voting
)
