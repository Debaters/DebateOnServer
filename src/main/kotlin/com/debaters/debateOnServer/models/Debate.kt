package com.debaters.debateOnServer.models

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import org.springframework.stereotype.Component

data class Debate(
    val debateId: String,
    @GraphQLDescription("토론의 제목입니다.")
    val title: String,
    @GraphQLDescription("토론의 설명입니다. 50자를 넘어가지 않습니다.")
    val description: String,
    val creatorName: String,
    val creatorId: String,
)