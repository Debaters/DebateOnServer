package com.debaters.debateOnServer

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

// 각 요청에서 Authentication 객체를 뽑아내주는 컴포넌트
@Component
class ApiKeyConverter : ServerAuthenticationConverter {
    override fun convert(exchange: ServerWebExchange?): Mono<Authentication> {
        return Mono.justOrEmpty(exchange)
            .flatMap {
                Mono.justOrEmpty(it.request.headers["api-key"])
            }
            .filter { it.isNotEmpty() }
            .map {
                it[0]
            }
            .map {
                ApiKeyToken(apiKey = it)
            }
    }
}