package com.jissay.unlocked.controllers.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class DummyRestController {

    @GetMapping("/test")
    fun testSecurity(): String {
        return "Test API Security"
    }
}
