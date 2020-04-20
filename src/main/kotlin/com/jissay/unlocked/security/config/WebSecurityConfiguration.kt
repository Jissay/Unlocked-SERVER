package com.jissay.unlocked.security.config


import com.jissay.unlocked.security.config.filters.JwtRequestFilter
import com.jissay.unlocked.services.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfiguration(private val accountService: AccountService,
                               private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
                               private val jwtRequestFilter: JwtRequestFilter)
    : WebSecurityConfigurerAdapter()
{
    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder)
    {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService<UserDetailsService>(this.accountService)
            .passwordEncoder(this.passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? { return BCryptPasswordEncoder(10) }

    override fun configure(httpSecurity: HttpSecurity)
    {
        // We don't need CSRF for this example
        // dont authenticate this particular request
        httpSecurity.csrf().disable()
                // all other requests need to be authenticated
                .authorizeRequests().antMatchers("/authenticate").permitAll().anyRequest()
                // make sure we use stateless session; session won't be used to
                .authenticated().and().exceptionHandling()
                // store user's state.
                .authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun accountAuthenticationManager(): AuthenticationManager?
    {
        val providers: MutableList<AuthenticationProvider> = ArrayList()
        providers.add(this.authenticationProvider())
        return ProviderManager(providers)
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider
    {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(this.accountService)
        authenticationProvider.setPasswordEncoder(this.passwordEncoder())
        return authenticationProvider
    }
}
