package com.jissay.unlocked.controllers.api.auth

import com.jissay.unlocked.entities.http.requests.JwtRequest
import com.jissay.unlocked.entities.http.responses.JwtResponse
import com.jissay.unlocked.security.oauth.JwtTokenUtil
import com.jissay.unlocked.services.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
class JwtAuthenticationController(val authenticationManager: AuthenticationManager,
                                  val jwtTokenUtil: JwtTokenUtil,
                                  val accountService: AccountService)
{

    @RequestMapping(value = ["/authenticate"], method = [RequestMethod.POST])
    fun createAuthenticationToken(@RequestBody authenticationRequest: JwtRequest): ResponseEntity<JwtResponse>
    {
        /* Authenticate the username / password, if wrong, exception are thrown */
        this.authenticate(authenticationRequest.username, authenticationRequest.password)

        /* Load the account */
        val account = this.accountService.loadUserByUsername(authenticationRequest.username)

        /* If there is a token, we return it, else we throw an exception */
        this.jwtTokenUtil.generateToken(account)?.let { token ->
            return ResponseEntity.ok(JwtResponse(token))
        }
        ?: throw Exception("BAD_TOKEN")
    }

    private fun authenticate(username: String, password: String)
    {
        try
        {
            this.authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
        }
        catch (e: DisabledException) { throw Exception("USER_DISABLED", e) }
        catch (e: BadCredentialsException) { throw Exception("BAD_CREDENTIALS", e) }
    }
}