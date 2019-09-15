package ru.vtb.vtbhack.socket

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import ru.vtb.vtbhack.entity.User
import ru.vtb.vtbhack.entity.Voting
import ru.vtb.vtbhack.persistence.AnswerRepository
import ru.vtb.vtbhack.persistence.VotingRepository
import ru.vtb.vtbhack.service.StatisticService
import java.util.concurrent.atomic.AtomicLong
import kotlin.math.log

private val logger = KotlinLogging.logger {}

class Message(val type: String, val votings: Any)
class SessionInfo(val id: Int, val roomId: Int)

@Component
class RoomHandler : TextWebSocketHandler() {
    @Autowired
    lateinit var answerRepository: AnswerRepository
    @Autowired
    lateinit var votingRepository: VotingRepository

    val sessionList = HashMap<WebSocketSession, SessionInfo>()

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        logger.info { "session dropped $session" }
        sessionList -= session
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        logger.info { "got session $session" }
    }

    public override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val json = ObjectMapper().readTree(message.payload)
        when (json.get("type").asText()) {
            "join" -> {
                logger.info { json }
                sessionList[session] = SessionInfo(json.get("user_id").textValue().toInt(), json.get("room_id").textValue().toInt())
            }
            "vote" -> {
                logger.info { json }
                val currentRoomId = json.get("room_id").textValue().toInt()
                val answerId = json.get("answer_id").textValue().toInt()
                if (json.get("increment").textValue().toInt() == 1) {
                    answerRepository.increment(answerId.toLong())
                } else {
                    answerRepository.decrement(answerId.toLong())
                }
                val groupList = sessionList.filter { it.value.roomId == currentRoomId }.map { it.key }.toList()
                broadcast(groupList, Message("VOTE", votingRepository.findAllByRoomId(currentRoomId.toLong())))
            }
        }
    }

    fun emit(session: WebSocketSession, msg: Message) = session.sendMessage(TextMessage(jacksonObjectMapper().writeValueAsString(msg)))
    fun broadcast(sessions: List<WebSocketSession>, msg: Message) = sessions.forEach { emit(it, msg) }
//    fun broadcastToOthers(me: WebSocketSession, msg: Message) = sessionList.filterNot { it.key == me }.forEach { emit(it.key, msg) }
}