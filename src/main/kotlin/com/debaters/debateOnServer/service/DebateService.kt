package com.debaters.debateOnServer.service

import com.debaters.debateOnServer.models.Debate
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component

@Component
class DebateService {
    /**
     * 현재 mock 데이터를 리턴함.
     */
    @ExperimentalStdlibApi
    suspend fun getDebates(): List<Debate> {
        return buildList {
            repeat(30) {
                addAll(
                    listOf(
                        Debate(
                            debateId = "0",
                            title = "짜장면은 찍먹이냐 부먹이냐",
                            description = "논란의 정점을 찍어보도록 해요",
                            creatorName = "zimin",
                            creatorId = "a",
                        ),
                        Debate(
                            debateId = "2",
                            title = "백신은 화이지/아스트라제네카",
                            description = "백신은 잘 골라야 해 ",
                            creatorName = "zimin",
                            creatorId = "a",
                        ),
                        Debate(
                            debateId = "3",
                            title = "iOS/Android",
                            description = "What's your favorite mobile os?",
                            creatorName = "zimin",
                            creatorId = "a",
                        ),
                        Debate(
                            debateId = "4",
                            title = "Windows/Max",
                            description = "What's your favorite OS?",
                            creatorName = "zimin",
                            creatorId = "a",
                        ),
                    )
                )
            }
        }
    }
}