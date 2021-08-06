package com.debaters.debateOnServer

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.stereotype.Component

@Component
class ApiAuthProvider : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        checkNotNull(authentication)

        if(authentication.isAuthenticated) return authentication

        return when {
            authentication.principal.toString() == "dev-user-key" -> {
                val token = ApiKeyToken(
                    AuthorityUtils.createAuthorityList("dev-user"),
                    authentication.principal.toString()
                )
                token.isAuthenticated = true
                token
            }
            authentication.principal.toString() == "admin-key" -> {
                val token = ApiKeyToken(
                    AuthorityUtils.createAuthorityList("admin"),
                    authentication.principal.toString()
                )
                token.isAuthenticated = true
                token
            }
            else -> {
                throw org.springframework.security.access.AccessDeniedException("invalid key")
            }
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return ApiKeyToken::class.java.isAssignableFrom(authentication)
    }
}