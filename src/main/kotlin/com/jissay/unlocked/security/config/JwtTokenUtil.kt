package com.jissay.unlocked.security.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.io.Serializable
import java.util.Date
import java.util.HashMap
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

/* See https://medium.com/swlh/spring-boot-security-jwt-hello-world-example-b479e457664c */
@Component
class JwtTokenUtil(private var ulProperties: ULProperties) : Serializable {
    val tokenValidity = 5 * 60 * 60

    fun getUsernameFromToken(token: String): String {
        return this.getAllClaimsFromToken(token).subject
    }

    fun getExpirationDateFromToken(token: String): Date {
        return this.getAllClaimsFromToken(token).expiration
    }

    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser().setSigningKey(ulProperties.jwtPrivateKey).parseClaimsJws(token).body
    }

    private fun isTokenExpired(token: String): Boolean? {
        val expiration: Date = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    // generate token for user
    fun generateToken(userDetails: UserDetails): String? {
        val claims: Map<String, Any> = HashMap()
        return doGenerateToken(claims, userDetails.username)
    }

    // while creating the token -
    // 1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    // 2. Sign the JWT using the HS512 algorithm and secret key.
    // 3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private fun doGenerateToken(
        claims: Map<String, Any>,
        subject: String
    ): String? {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + this.tokenValidity * 1000))
            .signWith(SignatureAlgorithm.HS512, this.ulProperties.jwtPrivateKey).compact()
    }

    // validate token
    fun validateToken(token: String, userDetails: UserDetails): Boolean? {
        val username: String = getUsernameFromToken(token)
        return username == userDetails.username && !isTokenExpired(token)!!
    }
}
