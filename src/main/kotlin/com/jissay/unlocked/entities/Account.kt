package com.jissay.unlocked.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NaturalId
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Size

@Entity
open class Account(
                @Column(nullable = false)
                var email: String,

                @NaturalId
                @Column(unique = true, nullable = false)
                @Size(min = 3, max = 255)
                var username: String = email,

                @Size(min = 8)
                @get:JsonIgnore
                @set:JsonProperty
                var password: String? = null
)
{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    var id: String? = null

    @Column(nullable = false)
    val created: Date = Date()

    open var enabled = true
}