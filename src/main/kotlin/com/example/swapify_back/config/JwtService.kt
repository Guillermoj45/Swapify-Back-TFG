package com.example.swapify_back.config

import com.example.swapify_back.DTO.TokenDTO
import com.example.swapify_back.Entities.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*

@Service
class JwtService {

    @Value("\${JWT_SECRET}")
    private val secretKey: String? = null

    //Método que me genera el token
    fun generateToken(user: User): String {
        val claims: MutableMap<String, Any?> = LinkedHashMap()
        claims["email"] = user.email
        claims["rol"] = user.rol.name
        claims["fecha_creacion"] = System.currentTimeMillis()
        claims["fecha_expiracion"] = System.currentTimeMillis() + 1000 * 60 * 60 * 12

        return Jwts
            .builder()
            .setClaims(claims)
            .signWith(getSignInKey(), SignatureAlgorithm.HS512)
            .compact()
    }


    // Función extraer los datos del todo el token
    fun extractDatosToken(token: String?): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    // Función que me extrae los datos del usuario eliminando toda la seguridad y header del token
    fun extractTokenData(token: String): TokenDTO {
        var token = token
        token = token.trim { it <= ' ' }

        val claims = extractDatosToken(token)

        return TokenDTO(
            claims["email"] as String,
            claims["rol"] as String,
            claims["fecha_creacion"] as Long,
            claims["fecha_expiracion"] as Long
        )
    }

    //Método que me comprueba si el token está expirado
    fun isExpired(token: String): Boolean {
        return Date(extractTokenData(token).fecha_expiracion.toString()).before(Date())
    }

    //Metodo que me decodifica el token
    private fun getSignInKey(): Key {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun desEncriptToken(token: String): String {
        var token = token
        if (token.startsWith("Bearer ")) {
            token = token.substring(7).trim { it <= ' ' }
        }
        return token
    }

}