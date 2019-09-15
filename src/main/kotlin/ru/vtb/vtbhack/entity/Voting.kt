package ru.vtb.vtbhack.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.google.gson.annotations.Expose
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
        @Expose
        val room: Room,

        @OneToMany(mappedBy = "voting")
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
        @Expose
        val voting: Voting
)
