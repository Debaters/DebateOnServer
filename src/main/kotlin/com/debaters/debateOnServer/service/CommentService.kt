package com.debaters.debateOnServer.service

import com.debaters.debateOnServer.models.Comment
import com.debaters.debateOnServer.repositories.DebatesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime

@Service
class CommentService {

    @Autowired
    private lateinit var debateRepository: DebatesRepository

    suspend fun writeComment(debateId: String, comment: Comment): Boolean {
        val debate = debateRepository.findById(debateId).get()
        debate.comments.add(comment)
        val newDebate = debate.copy(updatedTimestamp = OffsetDateTime.now())
        debateRepository.save(newDebate)
        return true
    }
}