package com.debaters.debateOnServer.service

import com.debaters.debateOnServer.models.Comment
import com.debaters.debateOnServer.repositories.CommentsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import kotlin.math.min

private const val DEFAULT_PAGE_SIZE = 10

@Service
class CommentService {

    @Autowired
    private lateinit var commentsRepository: CommentsRepository

    @ExperimentalStdlibApi
    suspend fun getComments(debateId: String, offset: Int = 0, size: Int = DEFAULT_PAGE_SIZE): List<Comment> {
        return withContext(Dispatchers.IO) {
            val comments = commentsRepository.findAllByDebateId(debateId)
            comments.subList(offset, offset + min(size, comments.size))
        }
    }

    suspend fun writeComment(debateId: String, comment: Comment): Boolean {
        commentsRepository.save(comment.copy(debateId = debateId))
        return true
    }
}