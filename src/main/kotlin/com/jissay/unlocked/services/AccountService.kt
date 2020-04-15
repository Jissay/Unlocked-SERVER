package com.jissay.unlocked.services

import com.jissay.unlocked.repositories.AccountRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

// See https://medium.com/@iamdeepaksp/spring-security-for-kotlin-4b8fb5e7ed1e

@Service
class AccountService(private val accountRepository: AccountRepository) : UserDetailsService
{
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails
    {
        val account = accountRepository.findByUsername(username)
                ?: throw UsernameNotFoundException("Could not find account with username $username!")

        return with(account)
        {
            User.withUsername(username)
                    .password(password)
                    .authorities("USER")
                    .build()
        }
    }


    //TODO: intercepting OAuth authentication via third party authorization services to create or update user accounts for our service.
    // see : https://github.com/venasolutions/sample-authorization-server/blob/master/src/main/kotlin/org/vena/example/service/OAuthAccountDetailWriterFactory.kt
//    override fun saveOAuth2Account(oAuth2Authentication: OAuth2Authentication): Account {
//        val userAuthentication = oAuth2Authentication.userAuthentication
//        val details = userAuthentication.details as Map<*, *>
//        val accountWriter = oAuthAccountDetailWriterFactory
//                .getAccountDetailWriter(oAuth2Authentication.oAuth2Request.clientId)
//        val username = userAuthentication.principal as String
//
//        return accountRepository.findByUsername(username)
//                ?: accountRepository.save(accountWriter.createAccount(username, details))
//    }
}