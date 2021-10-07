package com.debaters.debateOnServer.models

import com.expediagroup.graphql.generator.annotations.GraphQLDescription

/**
 * 토론 하나에 추가되는 댓글을 표현
 */
data class Comment(
        @GraphQLDescription("댓글 내용")
        val content: String,
        @GraphQLDescription("작성자 이름")
        val writerName: String,
)