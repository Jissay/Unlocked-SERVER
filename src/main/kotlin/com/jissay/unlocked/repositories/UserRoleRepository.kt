package com.jissay.unlocked.repositories

import com.jissay.unlocked.entities.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRoleRepository: JpaRepository<UserRole, Long>
{
    fun findByName(name: String): UserRole
}