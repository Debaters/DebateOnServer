package com.debaters.debateOnServer.service

import com.debaters.debateOnServer.models.Comment
import com.debaters.debateOnServer.models.Debate
import com.debaters.debateOnServer.repositories.CommentsRepository
import com.debaters.debateOnServer.repositories.DebatesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime

private const val DEFAULT_PAGE_SIZE = 10

@Service
class CommentService {

    @Autowired
    private lateinit var commentsRepository: CommentsRepository

    @ExperimentalStdlibApi
    suspend fun getComments(debateId: String, offset: Int = 0, size: Int = DEFAULT_PAGE_SIZE): List<Comment> {
        return commentsRepository.findAllByDebateId(debateId).subList(offset, offset + size)
    }

    suspend fun writeComment(debateId: String, comment: Comment): Boolean {
        commentsRepository.save(comment.copy(debateId = debateId))
        return true
    }
}