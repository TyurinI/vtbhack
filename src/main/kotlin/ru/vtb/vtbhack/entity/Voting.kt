package ru.vtb.vtbhack.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jdk.nashorn.internal.ir.annotations.Ignore
import javax.persistence.*

@Entity
@Table
class Voting(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val title: String,
        val type: String,

        @ManyToOne
        @JsonIgnore
        val room: Room,

        @OneToMany(mappedBy = "voting", fetch = FetchType.LAZY)
        val answers: List<Answer> = mutableListOf()
) //{
//    fun toDTO(): VotingDTO {
//        return VotingDTO(
//                id = this.id,
//                title = this.title,
//                type = this.type,
//                answers = this.answers
//        )
//    }
//}

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
