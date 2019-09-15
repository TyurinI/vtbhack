package ru.vtb.vtbhack.entity

import com.fasterxml.jackson.annotation.JsonIgnore
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
)

@Entity
@Table
class Answer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val value: String,
        @Column
        val voted: Int = 0,

        @ManyToOne
        @JsonIgnore
        val voting: Voting
)
