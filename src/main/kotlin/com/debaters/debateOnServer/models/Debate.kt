package com.debaters.debateOnServer.models

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.annotations.GraphQLType
import graphql.scalars.ExtendedScalars
import graphql.scalars.datetime.DateTimeScalar
import graphql.schema.GraphQLScalarType
import org.springframework.cglib.core.Local
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.stereotype.Component
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime

@Document("debate")
data class Debate(
        @Id
        val id: String = "",
        @GraphQLDescription("토론의 제목입니다.")
        val title: String,
        @GraphQLDescription("토론의 설명입니다. 50자를 넘어가지 않습니다.")
        val description: String,
        val creatorName: String,
        val creatorId: String,
        val createdTimestamp: OffsetDateTime = OffsetDateTime.now(),
        val updatedTimestamp: OffsetDateTime = OffsetDateTime.now(),
        val comments: MutableList<Comment> = mutableListOf()
)