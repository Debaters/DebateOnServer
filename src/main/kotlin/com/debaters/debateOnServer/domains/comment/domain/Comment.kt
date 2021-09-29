package com.debaters.debateOnServer.domains.comment.domain

import java.time.LocalDateTime

data class Comment(
    val commentId: String,
    val comment: String,
    val debateId: String,
    val writerId: String,
    val writerName: String,
    val writtenDateTime: String
)
