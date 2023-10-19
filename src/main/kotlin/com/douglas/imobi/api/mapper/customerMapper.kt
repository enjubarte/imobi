package com.douglas.imobi.api.mapper

import com.douglas.imobi.api.dto.CustomerDTO
import com.douglas.imobi.api.model.Customer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

class CustomerToDTO: Function<CustomerDTO>{
    fun apply(customer: Customer): CustomerDTO {
        return CustomerDTO(
            customer.name,
            customer.lastName,
            customer.email,
            customer.password,
            customer.telephone
        )
    }
}

class DTOToCustomer: Function<Customer>{
     fun apply(customerDTO: CustomerDTO): Customer {
        return Customer(
            UUID.randomUUID(),
            customerDTO.name,
            customerDTO.lastName,
            customerDTO.email,
            BCryptPasswordEncoder().encode(customerDTO.password),
            customerDTO.telephone
        )
    }
}