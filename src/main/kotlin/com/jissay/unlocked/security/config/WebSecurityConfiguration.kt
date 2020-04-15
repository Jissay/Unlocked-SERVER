package com.jissay.unlocked.security.config


import com.jissay.unlocked.services.AccountService
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class WebSecurityConfiguration(private val accountService: AccountService,
                               private val passwordEncoder: PasswordEncoder)
    : WebSecurityConfigurerAdapter()
{
    override fun configure(http: HttpSecurity)
    {
        http.csrf().disable()
        http.authorizeRequests()
                .antMatchers("/api/*").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll()
    }

    override fun configure(auth: AuthenticationManagerBuilder)
    {
        auth.userDetailsService(this.accountService)
                .passwordEncoder(this.passwordEncoder)
    }
}
