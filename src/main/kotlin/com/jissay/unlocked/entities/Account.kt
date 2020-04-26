package com.jissay.unlocked.entities

import com.jissay.unlocked.constants.ApplicationRoles
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Size
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NaturalId
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
open class Account(
    @Column(nullable = false)
    var email: String,

    @NaturalId
    @Column(unique = true, nullable = false)
    @Size(min = 3, max = 255)
    private var username: String = email,

    @Size(min = 8)
    private var password: String
) : UserDetails {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    var id: String? = null

    @Column(nullable = false)
    val created: Date = Date()

    open var enabled = true

    //region UserDetails implementation

    override fun getUsername(): String {
        return this.username
    }

    override fun getPassword(): String {
        return this.password
    }

    /**
     * SEE <MYSAM BACKEND>
     * Since a "getPassword()" method has to be defined (because of [UserDetails] specification), we need to implement
     * a "setPassword()" as well, in order to keep our "password" field private (this way, Kotlin does not generate
     * a "get/set" for this field, and doesn't clash with the declaration of "getPassword()"
     */
    fun setPassword(password: String) {
        this.password = password
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return hashSetOf(SimpleGrantedAuthority(ApplicationRoles.ROLE_ACCOUNT.name))
    }

    override fun isEnabled(): Boolean {
        return this.enabled
    }

    override fun isCredentialsNonExpired(): Boolean {
        return this.enabled
    }

    override fun isAccountNonExpired(): Boolean {
        return this.enabled
    }

    override fun isAccountNonLocked(): Boolean {
        return this.enabled
    }

    // end region
}
