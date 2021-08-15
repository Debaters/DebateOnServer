package com.debaters.debateOnServer

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

private val demo_key = "demoKeyOfApi"

class KeyNotFoundException : AuthenticationException("Invalid key")

// authentication: 이 요청이 어떤 유저인지/유효한 유저인 확인
class ApiKeyAuthManager : ReactiveAuthenticationManager {
    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        return Mono.justOrEmpty(authentication)
            .filter {
                it.credentials == demo_key
            }
            .switchIfEmpty( Mono.error(KeyNotFoundException()))
    }
}

// 부모 생정자에는 부여할 권한이 들어간다.
class ApiKeyToken(val apiKey: String) : AbstractAuthenticationToken(
    listOf(SimpleGrantedAuthority("ROLE_$API_USER"))
) {

    override fun isAuthenticated(): Boolean {
        return true
    }

    override fun getCredentials(): Any {
        return apiKey
    }

   override fun getPrincipal(): Any {
        return apiKey
    }
}