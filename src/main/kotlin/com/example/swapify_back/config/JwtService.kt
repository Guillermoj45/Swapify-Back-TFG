package com.example.swapify_back.config

import com.example.swapify_back.DTO.TokenDTO
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import com.example.swapify_back.entities.User
import io.jsonwebtoken.io.Decoders
import java.security.Key
import java.util.*

@Service
class JwtService {
    @Value("\${JWT_SECRET}")
    private lateinit var SECRET_KEY: String

    fun generateToken(user: User): String {
        return Jwts.builder()
            .setSubject(user.email)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24 hours
            .signWith(getSigningKey())
            .compact()
    }

    fun extractUsername(token: String): String {
        return extractClaim(token) { claims -> claims.subject }
    }

    fun isTokenValid(token: String, user: User): Boolean {
        val username = extractUsername(token)
        return username == user.email && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token) { claims -> claims.expiration }
    }

    private fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun getSigningKey(): Key {
        val keyBytes = Base64.getDecoder().decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun extractTokenData(token1: String): TokenDTO {
        var token = token1
        token = token.trim { it <= ' ' }

        val claims = extractDatosToken(token)

        return TokenDTO(
            claims["email"] as String,
            claims["rol"] as String,
            claims["fecha_creacion"] as Long,
            claims["fecha_expiracion"] as Long
        )
    }

    fun extractDatosToken(token: String?): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun getSignInKey(): Key {
        val keyBytes = Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun isExpired(token: String): Boolean {
        return Date(extractTokenData(token).fecha_expiracion.toString()).before(Date())
    }
}