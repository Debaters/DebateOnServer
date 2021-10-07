package com.debaters.debateOnServer.service

import com.debaters.debateOnServer.models.Comment
import com.debaters.debateOnServer.repositories.DebatesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommentService {

    @Autowired
    private lateinit var debateRepository: DebatesRepository

    suspend fun writeComment(debateId: String, comment: Comment): Boolean {
        val debate = debateRepository.findById(debateId).get()
        debate.comments.add(comment)
        debateRepository.save(debate)
        return true
    }
}