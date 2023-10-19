package com.douglas.imobi.api.service

import com.douglas.imobi.api.dto.CustomerDTO
import com.douglas.imobi.api.mapper.CustomerToDTO
import com.douglas.imobi.api.mapper.DTOToCustomer
import com.douglas.imobi.api.model.Customer
import com.douglas.imobi.api.repository.CustomerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CustomerService(
    private val repository: CustomerRepository,
    private val jwtService: JWTService
): UserDetailsService {

    fun findAll() = repository.findAll()

    fun findByEmail(email: String) = repository.findByEmail(email)

    fun findById(id: UUID): Customer{
        return repository.findById(id).get()
    }

    fun create(customerDTO: CustomerDTO): ResponseEntity<Any> =
        ResponseEntity(
            repository.save(DTOToCustomer().apply(customerDTO)),
            HttpStatus.CREATED
    )

    fun update(id: UUID,token: String, customerDTO: CustomerDTO): Customer{
        return repository.findById(id).get()
    }

    fun delete(id: UUID, token: String): ResponseEntity<Any> {
        return if(repository.existsById(id) && jwtService.isValid(token, repository.findById(id))){
            repository.deleteById(id)
            ResponseEntity(HttpStatus.NO_CONTENT)
        }else{
            ResponseEntity("CUSTOMER NOT FOUND", HttpStatus.NOT_FOUND)
        }
    }

    fun userExists(customerDTO: CustomerDTO): Boolean = customerDTO.email?.let { repository.findByEmail(it) } != null

    override fun loadUserByUsername(email: String): Customer =
        email.let { repository.findByEmail(email) }
}