package com.debaters.debateOnServer

import com.expediagroup.graphql.server.operations.Query
import org.springframework.stereotype.Component


@Component
class DebatesQuery : Query {
    fun titles() = listOf("korean and japan", "new idea")
    fun names() = listOf("Zimin", "jack", "casy")
    fun contact() = "010-1111-1111"
}