package com.debaters.debateOnServer.repositories

import com.debaters.debateOnServer.models.Comment
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component

@Component
interface CommentsRepository : MongoRepository<Comment, String> {
    // 요것은 queryDsl 의 매직. 처음안것이라 주석 추가
    fun findAllByDebateId(debateId: String): List<Comment>
}