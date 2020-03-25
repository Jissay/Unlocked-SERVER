package com.jissay.unlocked.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import kotlin.reflect.full.isSubclassOf

class UserAuthenticationProvider: DaoAuthenticationProvider()
{
    override fun supports(authentication: Class<*>): Boolean
    {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}