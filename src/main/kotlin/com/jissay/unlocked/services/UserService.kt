package com.jissay.unlocked.services

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService
( //https://medium.com/@iamdeepaksp/spring-security-for-kotlin-4b8fb5e7ed1e
) : UserDetailsService
{
    override fun loadUserByUsername(username: String?): UserDetails
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}