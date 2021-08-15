package com.debaters.debateOnServer
import com.debaters.debateOnServer.models.Debate
import com.debaters.debateOnServer.service.DebateService
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
    fun contact() = "010-1111-1111"

    suspend fun homeDebates() = debateService.getDebates()
}