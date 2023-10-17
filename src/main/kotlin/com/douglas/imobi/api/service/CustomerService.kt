package com.douglas.imobi.api.service

import com.douglas.imobi.api.dto.CustomerDTO
import com.douglas.imobi.api.model.Customer
import com.douglas.imobi.api.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CustomerService(
    private val repository: CustomerRepository
) {

    fun findAll() = repository.findAll()

    fun findByEmail(email: String) = repository.findByEmail(email)

    fun findById(id: UUID): Customer{
        return repository.findById(id).get()
    }

    fun update(id: UUID, customerDTO: CustomerDTO): Customer{
        return repository.findById(id).get()
    }

    fun delete(id: UUID) =  repository.deleteById(id)
}