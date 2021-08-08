package com.debaters.debateOnServer

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant
import java.util.*
import javax.xml.crypto.Data

@Service
class JwtChecker {

    val keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256)

    fun createJWT(subject: String): String {

        return Jwts.builder()
                .signWith(keyPair.private, SignatureAlgorithm.RS256)
                .setSubject(subject)
                .setIssuer("identity")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now() + Duration.ofDays(7)))
                .compact()
    }

    fun validate(jwtString: String): Jws<Claims> {
        return Jwts.parserBuilder()
                .setSigningKey(keyPair.public)
                .build()
                .parseClaimsJws(jwtString)
    }

}