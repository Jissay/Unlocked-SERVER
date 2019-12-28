package com.jissay.unlocked.entities

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class User: ConnectableUser()
{
    @Column(nullable = false)
    val created: Date = Date()
}