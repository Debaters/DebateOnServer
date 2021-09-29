package com.debaters.debateOnServer.domains.comment.api

import com.debaters.debateOnServer.domains.comment.application.CommentService
import com.debaters.debateOnServer.domains.comment.domain.Comment
import com.expediagroup.graphql.server.operations.Query
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@ExperimentalStdlibApi
@Component
class CommentQuery : Query {

    @Autowired
    lateinit var commentService: CommentService

    suspend fun getComments() = commentService.getComments()
}