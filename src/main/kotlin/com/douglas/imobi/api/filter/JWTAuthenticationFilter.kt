package com.douglas.imobi.api.filter

import com.douglas.imobi.api.repository.CustomerRepository
import com.douglas.imobi.api.service.JWTService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JWTAuthenticationFilter: OncePerRequestFilter() {
    private lateinit var jwtService: JWTService
    private lateinit var repository: CustomerRepository
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization")

        if (header == null || !header.startsWith("Bearer "))
            filterChain.doFilter(request,response)

        val token = header?.substring(7)
        val customerID = token?.let { jwtService.getIdByToken(it) }

        if (customerID != null && SecurityContextHolder.getContext() == null) {
            val customer = repository.findById(customerID)
            if (customer.isPresent) {
                val auth = UsernamePasswordAuthenticationToken(
                    customer,
                    null,
                    customer.get().authorities
                )
                auth.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = auth
                filterChain.doFilter(request, response)
            }
        }
    }
}