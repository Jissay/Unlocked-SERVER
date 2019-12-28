package com.jissay.unlocked.repositories

import com.jissay.unlocked.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, String>
{
    fun findByEmail(email: String): User?
    fun findByAccountName(accountName: String): User?
}