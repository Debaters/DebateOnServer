package com.debaters.debateOnServer

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

/**
 * 각 요청마다 Convert 로직을 처리하며 Authentication 객체를 뽑아냄.
 */
@Component
class JwtConverter : ServerAuthenticationConverter {
    override fun convert(exchange: ServerWebExchange?): Mono<Authentication> {
        return Mono.justOrEmpty(exchange)
                .flatMap { Mono.justOrEmpty(it.request.cookies["X-Auth"]) }
                .filter { it.isNotEmpty() }
                .map { it[0].value }
                .map { UsernamePasswordAuthenticationToken(it, it) }
    }
}