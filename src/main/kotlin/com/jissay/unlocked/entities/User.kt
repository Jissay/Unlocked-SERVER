package com.jissay.unlocked.entities

import org.hibernate.annotations.GenericGenerator
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors
import javax.persistence.*

@Entity(name = "unlocked_user")
class User: UserDetails
{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    var id: String? = null

    @Column(nullable = false)
    val created: Date = Date()

    @Column(nullable = false, unique = true)
    private lateinit var accountName: String

    @Column(nullable = false, unique = true)
    lateinit var email: String

    @Column(name = "password", nullable = false)
    private lateinit var pass: String

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var roles: Set<UserRole> = HashSet()

    open var enabled = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>
    {
        return this.roles
                .map { r -> SimpleGrantedAuthority(r.name) }
                .toMutableList()
    }

    override fun isEnabled(): Boolean { return this.enabled }

    override fun getPassword(): String { return this.pass }
    fun setPassword(password: String) { this.pass = password }

    override fun getUsername(): String { return this.accountName }
    fun setUsername(username: String) { this.accountName = username }

    override fun isCredentialsNonExpired(): Boolean { return this.isEnabled }
    override fun isAccountNonExpired(): Boolean { return this.isEnabled }
    override fun isAccountNonLocked(): Boolean { return this.isEnabled }
}