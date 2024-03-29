package ru.vtb.vtbhack.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.google.gson.annotations.Expose
import org.springframework.data.repository.cdi.Eager
import ru.vtb.vtbhack.DTO.RoomsResponseDTO
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "usrs")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val firstName:String,
        val secondName:String,
        @Column(unique = true)
        val email: String,
        val password: String,
        val phone: String,
        val uuid: UUID = UUID.randomUUID(),

        @ManyToMany(mappedBy = "usrs")
        @JsonIgnore
        @Expose
        val rooms: MutableList<Room>? = mutableListOf()
) {
    fun toShortRoomsResponse(): List<RoomsResponseDTO> {
        val response = mutableListOf<RoomsResponseDTO>()
        this.rooms?.forEach {
            response.add(RoomsResponseDTO(
                    id = it.id,
                    title = it.title,
                    isActual = it.endTime > Date(),
                    startTime = it.startTime,
                    endTime = it.endTime,
                    userCount = it.usrs.size,
                    fileCount = 42
            )
            )
        }
        return response
    }
}