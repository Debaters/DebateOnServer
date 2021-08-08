package com.debaters.debateOnServer

import io.jsonwebtoken.Jwt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@Component
class LoginComponent {

    val users = listOf(
            UserCredential(
                    "zimin",
                    "12345"
            )
    )
    @Autowired
    lateinit var jwtChecker: JwtChecker

    @PostMapping("/login")
    fun login(@RequestBody user: UserCredential): Mono<ResponseEntity<Void>>{
        return Mono.justOrEmpty(users.find { user.name == it.name })
                .map {
                    val jwt = jwtChecker.createJWT(it.name)

                    val authCookie = ResponseCookie.fromClientResponse("X-Auth", jwt)
                            .maxAge(3600)
                            .httpOnly(true)
                            .path("/")
                            .secure(false) // should be true in production
                            .build()

                    ResponseEntity.noContent()
                            .header("Set-cookie", authCookie.toString())
                            .build<Void>()
                }
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()))

    }
}

class UserCredential(
        val name: String,
        val password: String,
)