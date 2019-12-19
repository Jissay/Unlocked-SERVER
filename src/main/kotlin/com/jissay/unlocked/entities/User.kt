package com.jissay.unlocked.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
class User
(
    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false, unique = true)
    var username: String,

    @Column(nullable = false)
    val created: Date = Date()
)
{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    var id: String? = null
}