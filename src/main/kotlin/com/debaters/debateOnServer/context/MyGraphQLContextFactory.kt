package com.debaters.debateOnServer.global.common

import com.debaters.debateOnServer.context.MyGraphQLContext
import com.expediagroup.graphql.server.spring.execution.SpringGraphQLContext
import com.expediagroup.graphql.server.spring.execution.SpringGraphQLContextFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest

@Component
class MyGraphQLContextFactory : SpringGraphQLContextFactory<MyGraphQLContext>() {
    override suspend fun generateContext(request: ServerRequest): MyGraphQLContext = MyGraphQLContext(
        request = request,
        myCustomValue = request.headers().firstHeader("MyHeader") ?: "defaultContext"
    )
}