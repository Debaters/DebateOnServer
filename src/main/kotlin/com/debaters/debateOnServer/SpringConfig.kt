package com.debaters.debateOnServer

import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse


@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SpringConfig {

    @Bean
    @Throws(Exception::class)
    fun springWebFilterChain(
        http: ServerHttpSecurity,
        apiKeyConverter: ApiKeyConverter,
    ): SecurityWebFilterChain? {

        val apiAuthenticationManager = ApiKeyAuthManager()
        val apiKeyWebFilter = AuthenticationWebFilter(apiAuthenticationManager)
        apiKeyWebFilter.setServerAuthenticationConverter(apiKeyConverter)

        http
            .addFilterBefore(apiKeyWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .authorizeExchange()
            .pathMatchers("/graphql/**").hasRole(API_USER)
            .anyExchange().permitAll()
            .and()
            .httpBasic().disable()
            .csrf().disable()
            .formLogin().disable()
            .logout().disable()
            .httpBasic().authenticationEntryPoint(HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED)) // 로그인 비활성화 위해 추가

        return http.build()
    }

}