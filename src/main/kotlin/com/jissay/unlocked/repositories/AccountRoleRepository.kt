package com.jissay.unlocked.repositories

import com.jissay.unlocked.entities.AccountRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRoleRepository: JpaRepository<AccountRole, Long>
{
    fun findByName(name: String): AccountRole
}