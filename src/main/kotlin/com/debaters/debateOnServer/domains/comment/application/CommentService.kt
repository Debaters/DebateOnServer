package com.debaters.debateOnServer.domains.comment.application

import com.debaters.debateOnServer.domains.comment.domain.Comment
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CommentService {

    @ExperimentalStdlibApi
    private val mockList = buildList {
        addAll(
            listOf(
                Comment(
                    commentId = "1",
                    comment = "카카오하면 오택원이지",
                    writerId = "1",
                    writerName = "철수",
                    writtenDateTime = LocalDateTime.of(2021, 9, 15, 9, 0),
                    debateId = "1"
                ),
                Comment(
                    commentId = "2",
                    comment = "오택원은 네이버지",
                    writerId = "2",
                    writerName = "영희",
                    writtenDateTime = LocalDateTime.of(2021, 9, 15, 9, 1),
                    debateId = "1"
                ),
                Comment(
                    commentId = "3",
                    comment = "카카오 주식 왜 이럼?",
                    writerId = "1",
                    writerName = "철수",
                    writtenDateTime = LocalDateTime.of(2021, 9, 15, 9, 1),
                    debateId = "1"
                ),
                Comment(
                    commentId = "4",
                    comment = "난 15층에 물렸어",
                    writerId = "2",
                    writerName = "영희",
                    writtenDateTime = LocalDateTime.of(2021, 9, 15, 9, 2),
                    debateId = "1"
                ),
                Comment(
                    commentId = "5",
                    comment = "야나두",
                    writerId = "1",
                    writerName = "철수",
                    writtenDateTime = LocalDateTime.of(2021, 9, 15, 9, 2),
                    debateId = "1"
                )
            )
        )
    }.toMutableList()

    @ExperimentalStdlibApi
    suspend fun getComments(debateId: String): List<Comment> {
        return mockList.subList(0, 5)
    }
}