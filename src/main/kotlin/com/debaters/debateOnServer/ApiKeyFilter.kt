package com.debaters.debateOnServer

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class ApiKeyFilter : GenericFilterBean() {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        checkNotNull(request)
        checkNotNull(response)
        checkNotNull(chain)

        if (request is HttpServletRequest && response is HttpServletResponse) {
            val apiKey: String = request.getHeader(keyHeaderName) ?: ""
            if (apiKey.isBlank()) {
                response.status = 403
                chain.doFilter(request, response)
            } else {
                SecurityContextHolder.getContext().authentication = ApiKeyToken(emptyList(), "admin")
                chain.doFilter(request, response)
            }
        } else {
            chain.doFilter(request, response)
        }
    }
}

class ApiKeyToken(
    val authorities: List<GrantedAuthority>,
    val tokenName: String,
): AbstractAuthenticationToken(authorities) {

    init {
        isAuthenticated = true
    }

    override fun getCredentials(): Any {
        return Unit
    }

    override fun getPrincipal(): Any {
        return tokenName
    }
}