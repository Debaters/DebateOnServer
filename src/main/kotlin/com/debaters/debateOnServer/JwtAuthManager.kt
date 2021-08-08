package com.debaters.debateOnServer

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JwtAuthManager(val jwtChecker:  JwtChecker) : ReactiveAuthenticationManager{
    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        return Mono.just(authentication)
                .map { jwtChecker.validate(it.credentials as String) }
                .onErrorResume { Mono.empty() }
                .map { jws ->
                    UsernamePasswordAuthenticationToken(
                            jws.body.subject,
                            authentication.credentials as String,
                            listOf(SimpleGrantedAuthority("ROLE_USER"))
                    )
                }
    }
}