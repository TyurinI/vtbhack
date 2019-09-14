package ru.vtb.vtbhack.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import ru.vtb.vtbhack.DTO.RoomsResponseDTO
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "usrs")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column
        val email: String,
        @Column
        val password: String,

        @ManyToMany(mappedBy = "usrs", fetch = FetchType.LAZY)
        @JsonIgnore
        val rooms: MutableList<Room>? = mutableListOf()
) {
    fun toShortRoomsResponse(): List<RoomsResponseDTO> {
        val response = mutableListOf<RoomsResponseDTO>()
        this.rooms?.forEach {
            response.add(RoomsResponseDTO(
                    title = it.title,
                    isActual = it.endTime > Date(),
                    startTime = it.startTime,
                    endTime = it.endTime
            )
            )
        }
        return response
    }
}