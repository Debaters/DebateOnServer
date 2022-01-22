package com.debaters.debateOnServer.context

import com.expediagroup.graphql.server.spring.execution.SpringGraphQLContext
import org.springframework.web.reactive.function.server.ServerRequest

class MyGraphQLContext(
    request: ServerRequest,
    val myCustomValue: String
): SpringGraphQLContext(request);