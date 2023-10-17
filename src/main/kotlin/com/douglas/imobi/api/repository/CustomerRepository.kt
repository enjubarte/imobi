package com.douglas.imobi.api.repository

import com.douglas.imobi.api.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CustomerRepository: JpaRepository<Customer, UUID>{
    fun findByEmail(email: String): Customer
}