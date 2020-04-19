package com.jissay.unlocked.security.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
class ULProperties
{
    @Value("\${unlocked.jwt.privatekey}")
    lateinit var jwtPrivateKey: String

    @Value("\${unlocked.jwt.publickey}")
    lateinit var jwtPublicKey: String
}