package com.debaters.debateOnServer.service

import com.debaters.debateOnServer.models.Debate
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component

@Component
class DebateService {
    @ExperimentalStdlibApi
    private val mockList = buildList {
        repeat(30) {
            addAll(
                listOf(
                    Debate(
                        debateId = "${it * 4 + 0}",
                        title = "짜장면은 찍먹이냐 부먹이냐",
                        description = "논란의 정점을 찍어보도록 해요",
                        creatorName = "zimin",
                        creatorId = "a",
                    ),
                    Debate(
                        debateId = "${it * 4 + 1}",
                        title = "백신은 화이지/아스트라제네카",
                        description = "백신은 잘 골라야 해 ",
                        creatorName = "zimin",
                        creatorId = "a",
                    ),
                    Debate(
                        debateId = "${it * 4 + 2}",
                        title = "iOS/Android",
                        description = "What's your favorite mobile os?",
                        creatorName = "zimin",
                        creatorId = "a",
                    ),
                    Debate(
                        debateId = "${it * 4 + 3}",
                        title = "Windows/Max",
                        description = "What's your favorite OS?",
                        creatorName = "zimin",
                        creatorId = "a",
                    ),
                )
            )
        }
    }
    /**
     * 현재 mock 데이터를 리턴함.
     */
    @ExperimentalStdlibApi
    suspend fun getDebates(offset: Int = 0, size: Int = 10): List<Debate> {
        return mockList.subList(offset, offset + size)
    }

    @ExperimentalStdlibApi
    suspend fun findDebate(id: String): Debate? {
        return mockList.find { it.debateId == id }
    }
}