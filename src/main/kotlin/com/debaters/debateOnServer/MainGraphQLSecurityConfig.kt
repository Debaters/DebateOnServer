package com.debaters.debateOnServer

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

const val keyHeaderName = "api-key"

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
/**
 * 웹 api 접근시 http/web 에 따라 어떤 검사를 할 지 설정하는 클래스
 */
class MainGraphQLSecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        checkNotNull(http)
        http // api-key auth 필터 추가
            .addFilterBefore(ApiKeyFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            // session manager filter 사용 x
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable()
            .httpBasic().disable()
            // Disable the /logout filter
            .logout().disable()
            // Disable anonymous users
            .anonymous().disable()
    }

    override fun configure(web: WebSecurity?) {
        checkNotNull(web)
        web.ignoring().antMatchers("/playground")
    }
}
