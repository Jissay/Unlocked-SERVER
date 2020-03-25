package com.jissay.unlocked.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class UserRole(var name: String = "")
{
    @Id
    @GeneratedValue
    val id: Long? = null
}