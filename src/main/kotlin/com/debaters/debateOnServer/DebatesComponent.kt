package com.debaters.debateOnServer

import com.debaters.debateOnServer.models.Comment
import com.debaters.debateOnServer.models.Debate
import com.debaters.debateOnServer.service.CommentService
import com.debaters.debateOnServer.service.DebateService
import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Mutation
import com.expediagroup.graphql.server.operations.Query
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@ExperimentalStdlibApi
@Component
class DebatesQuery : Query {

    @Autowired
    lateinit var debateService: DebateService

    fun titles() = listOf("korean and japan", "new idea")
    fun names() = listOf("Zimin", "jack", "casy")
    fun members() = listOf("김", "김", "오",  "최", "변")
    fun contact() = "010-1111-1111"

    suspend fun homeDebates(offset: Int = 0, size: Int = 10) = debateService.getDebates(offset, size)

    suspend fun debate(id: String) = debateService.findDebate(id)
}

@ExperimentalStdlibApi
@Component
class DebateMutation : Mutation {
    @Autowired
    lateinit var debateService: DebateService

    @Autowired
    lateinit var commentService: CommentService

    suspend fun createDebate(
            @GraphQLDescription("토론의 제목입니다. 50자를 넘어가지 않습니다.")
            title: String,
            @GraphQLDescription("토론의 설명입니다. 100자를 넘어가지 않습니다.")
            description: String,
            @GraphQLDescription("작성자의 이름을 넣어주세요.")
            creatorName: String,
    ): Boolean {
        return debateService.createDebate(
                title,
                description,
                creatorName,
        )
    }

    suspend fun addComment(
            @GraphQLDescription("댓글을 추가할 Debate 의 id")
            debateId: String,
            @GraphQLDescription("추가할 댓글 정보")
            comment: Comment,
    ) : Boolean {
        return commentService.writeComment(debateId, comment)
    }
}