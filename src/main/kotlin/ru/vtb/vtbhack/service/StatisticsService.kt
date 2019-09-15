package ru.vtb.vtbhack.service

import org.springframework.stereotype.Component
import ru.vtb.vtbhack.entity.Answer
import java.util.ArrayList
import java.util.concurrent.atomic.AtomicInteger

@Component
class StatisticService {

    companion object {
        var statistics = ArrayList<StatInfo>()
        var total = AtomicInteger(0)
    }



    fun initAnswers(answers: List<Answer>) {
        answers.forEach { answer -> statistics.add(StatInfo(answer.id, 0)) }
        total.set(0)
    }

    data class StatInfo(var id: Long?, var count: Int)
}