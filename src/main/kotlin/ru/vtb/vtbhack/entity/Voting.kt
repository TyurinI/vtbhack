package ru.vtb.vtbhack.entity

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table
data class Voting(
        val title: String,
        val type: String
        )