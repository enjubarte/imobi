package com.douglas.imobi.api.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.douglas.imobi.api.model.Customer
import org.springframework.beans.factory.annotation.Value
import java.util.*
import java.util.concurrent.TimeUnit

class JWTService {
    @field:Value("\${jwt.secret}")
    private val secret: String = ""

    fun generateToken(id: UUID): String{
        return JWT.create()
            .withSubject(id.toString())
            .withExpiresAt(Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
            .sign(Algorithm.HMAC512(secret))
    }

    fun getIdByToken(token: String): UUID{
        return UUID.fromString(JWT.decode(token.replace("Bearer ", "")).subject)
    }

    fun isExpired(token: String): Boolean = JWT.decode(token).expiresAt.before(Date())

    fun isValid(token: String, customer: Optional<Customer>): Boolean = getIdByToken(token) == customer.get().id && isExpired(token)
}