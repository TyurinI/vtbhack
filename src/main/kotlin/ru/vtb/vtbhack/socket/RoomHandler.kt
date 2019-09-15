package ru.vtb.vtbhack.socket

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import mu.KotlinLogging
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import ru.vtb.vtbhack.persistence.VotingRepository

private val logger = KotlinLogging.logger {}

class Message(val type: String, val votings: Any)
class SessionInfo(val id: Long, val roomId: Long)

@Component
class RoomHandler(val votingRepository: VotingRepository, val jdbcTemplate: JdbcTemplate) : TextWebSocketHandler() {

    val sessionList = HashMap<WebSocketSession, SessionInfo>()

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        logger.info { "session dropped $session" }
        sessionList -= session
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        logger.info { "got session $session" }
    }

    public override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val json = Gson().fromJson(message.payload, WsMessage::class.java)
        when (json.type) {
            "join" -> {
//                logger.info { json }
//                val id = json.userId
//                logger.info { id }
//                val roomId = json.roomId
//                logger.info { roomId }
//                sessionList[session] = SessionInfo(id, roomId)
            }
            "vote" -> {
                logger.info { json }
                val id = json.userId
                logger.info { id }
                val roomId = json.roomId
                logger.info { roomId }
                sessionList[session] = SessionInfo(id, roomId)
                logger.info { json }
                val currentRoomId = json.roomId
                val answerId = json.answerId
                if (json.increment == 1) {
                    jdbcTemplate.execute("UPDATE answer set voted = voted + 1 WHERE id = ${answerId.toLong()}")
                } else {
                    jdbcTemplate.execute("UPDATE answer set voted = voted - 1 WHERE id = ${answerId.toLong()}")
                }
//                val groupList = sessionList.filter { it.value.roomId == currentRoomId }.map { it.key }.toList()
                val votings = votingRepository.getvot(currentRoomId)
                logger.info { votings }
//                broadcast(groupList, Message("VOTE", votings))
                broadcast(sessionList.keys.toList(), Message("VOTE", votings))
            }
        }
    }

    fun emit(session: WebSocketSession, msg: Message) = session.sendMessage(TextMessage(Gson().toJson(msg)))
    fun broadcast(sessions: List<WebSocketSession>, msg: Message) = sessions.forEach { emit(it, msg) }
    //    fun broadcastToOthers(me: WebSocketSession, msg: Message) = sessionList.filterNot { it.key == me }.forEach { emit(it.key, msg) }
    data class WsMessage(
            val type: String,
            @SerializedName("user_id") val userId: Long,
            @SerializedName("room_id") val roomId: Long,
            @SerializedName("increment") val increment: Int,
            @SerializedName("answer_id") val answerId: Int
            )
}