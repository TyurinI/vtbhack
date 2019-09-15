package ru.vtb.vtbhack.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table
data class Attachment(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val title: String,

        @ManyToOne
        @JsonIgnore
        val room: Room
)