package com.douglas.imobi.api.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant
import java.util.UUID


@JsonIgnoreProperties(
    "enabled", "authorities", "password", "username", "accountNonExpired", "accountNonLocked", "credentialsNonExpired")
@Entity(name = "tb_customers")
data class Customer(
    @field:Id @field: GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,
    @field:Column(nullable = false)
    var name: String?,
    @field:Column(nullable = false)
    var lastName: String?,
    @field:Column(unique = true, nullable = false)
    var email: String?,
    @field:Column(nullable = false)
    var password: String?,
    @field:Column(nullable = false)
    var telephone: String?,
    @field:Column(nullable = false)
    val createAt: Instant = Instant.now(),
    @field:Column(nullable = false)
    val updateAt: Instant = Instant.now()
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>  = mutableListOf()
    override fun getPassword(): String? = password
    override fun getUsername(): String? = email
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}