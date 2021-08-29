package com.debaters.debateOnServer

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

import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource


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

    @Bean
    fun corsWebFilter(): CorsWebFilter {
        val config = CorsConfiguration().apply {
            allowedOrigins = listOf("http://debaters.world")
            maxAge = 8000L
            allowedMethods = listOf("GET", "PUT")
            allowedHeaders = listOf("api-key")
        }

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)

        return CorsWebFilter(source)
    }
}