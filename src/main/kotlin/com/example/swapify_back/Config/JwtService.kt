package com.example.swapify_back.Config

import com.example.swapify_back.DTO.TokenDTO
import com.example.swapify_back.Entities.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import java.security.Key
import java.util.*


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
            .signWith(signInKey, SignatureAlgorithm.HS512)
            .compact()
    }


    // Función extraer los datos del todo el token
    fun extractDatosToken(token: String?): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(signInKey)
            .build()
            .parseClaimsJws(token)
            .body
    }

    // Función que me extrae los datos del usuario eliminando toda la seguridad y header del token
    fun extractTokenData(token: String): TokenDTO {
        val trimmedToken = token.trim { it <= ' ' }
        val claims = extractDatosToken(trimmedToken)

        return TokenDTO(
            email = claims["email"] as String?,
            rol = claims["rol"] as String?,
            fecha_creacion = claims["fecha_creacion"] as Long?,
            fecha_expiracion = claims["fecha_expiracion"] as Long?
        )
    }

    //Método que me comprueba si el token está expirado
    fun isExpired(token: String): Boolean {
        val fechaExpiracion = extractTokenData(token).fecha_expiracion ?: return true
        return Date(fechaExpiracion).before(Date())
    }

    private val signInKey: Key
        //Metodo que me decodifica el token
        get() {
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