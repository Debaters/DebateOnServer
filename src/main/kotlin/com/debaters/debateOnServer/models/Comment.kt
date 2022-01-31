package com.debaters.debateOnServer.models

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import org.springframework.data.annotation.QueryAnnotation
import org.springframework.data.mongodb.core.mapping.Document

/**
 * 토론 하나에 추가되는 댓글을 표현
 * Mongo db에 저장하는 모델. 추후 저장하는 모델, api 리턴 타입 분리해볼 것.
 */
@Document
data class Comment(
        @GraphQLDescription("이 댓글이 적힌 debate 의 id")
        val debateId: String,
        @GraphQLDescription("댓글 내용")
        val content: String,
        @GraphQLDescription("작성자 이름")
        val writerName: String,
)