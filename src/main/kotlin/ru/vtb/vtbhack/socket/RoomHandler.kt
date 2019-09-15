package ru.vtb.vtbhack.socket

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import ru.vtb.vtbhack.entity.User
import ru.vtb.vtbhack.service.StatisticService
import java.util.concurrent.atomic.AtomicLong

class Message(val msgType: String, val data: Any)
class SessionInfo(val id: Int, val roomId: Int)

class RoomHandler : TextWebSocketHandler() {
    val sessionList = HashMap<WebSocketSession, SessionInfo>()

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessionList -= session
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
    }

    public override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val json = ObjectMapper().readTree(message.payload)
        // {type: "join/say", data: "name/msg"}
        when (json.get("type").asText()) {
            "join" -> {
//                sessionList.put(session, SessionInfo(json.get("user_id").textValue(), json.get("room_id").textValue())
            }
            "vote" -> {
//                broadcastToOthers(session, Message("vote", user))
            }
        }
    }

    fun emit(session: WebSocketSession, msg: Message) = session.sendMessage(TextMessage(jacksonObjectMapper().writeValueAsString(msg)))
    fun broadcast(msg: Message) = sessionList.forEach { emit(it.key, msg) }
    fun broadcastToOthers(me: WebSocketSession, msg: Message) = sessionList.filterNot { it.key == me }.forEach { emit(it.key, msg) }
}