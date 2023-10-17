package com.douglas.imobi.api.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.UUID

@Entity
data class Customer(
    @field:Id @field: GeneratedValue(strategy = GenerationType.UUID)
    val uuid: UUID?,
    @field:Column(nullable = false)
    var name: String?,
    @field:Column(nullable = false)
    var lastName: String?,
    @field:Column(unique = true, nullable = false)
    var email: String?,
    @field:Column(nullable = false)
    var password: String?,
    @field:Column(nullable = false)
    var telephone: String?
)