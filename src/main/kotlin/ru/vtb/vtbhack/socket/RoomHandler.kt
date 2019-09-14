package ru.vtb.vtbhack.socket

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import ru.vtb.vtbhack.entity.User
import java.util.concurrent.atomic.AtomicLong

class Message(val msgType: String, val data: Any)
class RoomHandler : TextWebSocketHandler() {

    val sessionList = HashMap<WebSocketSession, User>()
    var uids = AtomicLong(0)

    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessionList -= session
    }

    public override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val json = ObjectMapper().readTree(message.payload)
        // {type: "join/say", data: "name/msg"}
        when (json.get("type").asText()) {
//            "join" -> {
//                val user = User(uids.getAndIncrement(), json.get("data").asText())
//                sessionList.put(session!!, user)
//                 tell this user about all other users
//                emit(session, Message("users", sessionList.values))
//                 tell all other users, about this user
//                broadcastToOthers(session, Message("join", user))
//            }
//            "say" -> {
//                broadcast(Message("say", json.get("data").asText()))
//            }
        }
    }

    fun emit(session: WebSocketSession, msg: Message) = session.sendMessage(TextMessage(jacksonObjectMapper().writeValueAsString(msg)))
    fun broadcast(msg: Message) = sessionList.forEach { emit(it.key, msg) }
    fun broadcastToOthers(me: WebSocketSession, msg: Message) = sessionList.filterNot { it.key == me }.forEach { emit(it.key, msg) }
}