package com.jissay.unlocked.services

import com.jissay.unlocked.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

// See https://medium.com/@iamdeepaksp/spring-security-for-kotlin-4b8fb5e7ed1e
@Service
open class UserAuthenticationDetailsService (private val userRepository: UserRepository) : UserDetailsService
{
    override fun loadUserByUsername(username: String): UserDetails
    {
        return this.userRepository.findByAccountName(username) ?: throw UsernameNotFoundException(username)
    }
}