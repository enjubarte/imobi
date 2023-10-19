package com.douglas.imobi.api.service

import com.douglas.imobi.api.dto.CustomerDTO
import com.douglas.imobi.api.model.Customer
import com.douglas.imobi.api.util.JWTResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtService: JWTService,
    private val customerService: CustomerService,
    private val authenticationManager: AuthenticationManager
) {
    fun signIn(customerDTO: CustomerDTO): ResponseEntity<Any> {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                customerDTO.email,
                customerDTO.password
            )
        )

        val customer: Customer? = customerDTO.email?.let { customerService.findByEmail(it) }

        return  if (customer != null) ResponseEntity(
            customer.id?.let { jwtService.generateToken(it) }?.let { JWTResponse(it) },
            HttpStatus.OK
        )
        else ResponseEntity("DEU MERDA AMIGO!!!!", HttpStatus.BAD_REQUEST)

    }

    fun signUp(customerDTO: CustomerDTO): ResponseEntity<Any>{
        return if (customerService.userExists(customerDTO))
            ResponseEntity("EMAIL JÁ CADASTRADO",
                HttpStatus.BAD_REQUEST
            )
        else customerService.create(customerDTO)
    }
}