package com.douglas.imobi.api.controller

import com.douglas.imobi.api.dto.CustomerDTO
import com.douglas.imobi.api.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/sign-in")
    fun signIn(@RequestBody() customerDTO: CustomerDTO) = authService.signIn(customerDTO)

    @PostMapping("/sign-up")
    fun signUp(@RequestBody() customerDTO: CustomerDTO) = authService.signUp(customerDTO)
}