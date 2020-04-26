package com.jissay.unlocked.repositories

import com.jissay.unlocked.entities.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
open interface AccountRepository : JpaRepository<Account, String> {
    fun findByUsername(username: String): Account?
}
