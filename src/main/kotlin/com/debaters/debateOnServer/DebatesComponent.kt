package com.debaters.debateOnServer

import com.debaters.debateOnServer.context.MyGraphQLContext
import com.debaters.debateOnServer.models.Comment
import com.debaters.debateOnServer.service.CommentService
import com.debaters.debateOnServer.service.DebateService
import com.debaters.debateOnServer.service.NicknameService
import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Mutation
import com.expediagroup.graphql.server.operations.Query
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.logging.Logger


@ExperimentalStdlibApi
@Component
class DebatesQuery : Query {

    val LOG = Logger.getLogger(this.javaClass.name)

    @Autowired
    lateinit var debateService: DebateService

    @Autowired
    lateinit var commentService: CommentService

    @Autowired
    lateinit var nicknameService: NicknameService

    fun titles() = listOf("korean and japan", "new idea")
    fun names() = listOf("Zimin", "jack", "casy")
    fun members() = listOf("김", "김", "오",  "최", "변")
    fun submarine() = listOf("잠수함패치의 증거")
    fun contact() = "010-1111-1111"

    suspend fun getComments(debateId: String, offset: Int, size: Int) =
            commentService.getComments(debateId, offset, size)

    suspend fun homeDebates(offset: Int = 0, size: Int = 10) = debateService.getDebates(offset, size)

    suspend fun debate(id: String) = debateService.findDebate(id)

    suspend fun getNickname(
        context: MyGraphQLContext
    ) : String {
        var userAgent: String = context.getHTTPRequestHeader("user-agent").toString()
        LOG.info("userAgent: \n$userAgent")
        return nicknameService.getNickNameById(userAgent)
    }
}

@ExperimentalStdlibApi
@Component
class DebateMutation : Mutation {

    val LOG = Logger.getLogger(this.javaClass.name)

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