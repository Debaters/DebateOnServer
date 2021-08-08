package com.debaters.debateOnServer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity.AuthorizeExchangeSpec
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.web.reactive.function.client.ExchangeStrategies.withDefaults


@SpringBootApplication
class DebateOnServerApplication

fun main(args: Array<String>) {
	runApplication<DebateOnServerApplication>(*args)
}

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class GraphConfig {
	@Bean
	@Throws(Exception::class)
	fun springWebFilterChain(
			http: ServerHttpSecurity,
			jwtAuthManager: JwtAuthManager,
			jwtConverter: JwtConverter,
	): SecurityWebFilterChain? {
		val authWebFilter = AuthenticationWebFilter(jwtAuthManager)
		authWebFilter.setServerAuthenticationConverter(jwtConverter)

		return http
				// Best practice to use both for defense in depth
				.authorizeExchange { requests: AuthorizeExchangeSpec -> requests.anyExchange().permitAll() }
				.addFilterAt(authWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
				.httpBasic().disable()
				.csrf().disable()
				.formLogin().disable()
				.logout().disable()
				.build()
	}

}