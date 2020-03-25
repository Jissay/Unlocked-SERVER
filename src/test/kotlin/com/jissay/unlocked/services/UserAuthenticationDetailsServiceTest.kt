package com.jissay.unlocked.services

import com.jissay.unlocked.entities.User
import com.jissay.unlocked.repositories.UserRepository
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.security.core.userdetails.UsernameNotFoundException

@RunWith(MockitoJUnitRunner::class)
@DataJpaTest
class UserAuthenticationDetailsServiceTest
{
    @InjectMocks
    private lateinit var userAuthenticationDetailsService: UserAuthenticationDetailsService

    @Mock
    private lateinit var userRepository: UserRepository // Dependency for UserAuthenticationDetailsService

    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    @Before
    fun init() { MockitoAnnotations.initMocks(this) }

    //region Tests

    @Test
    fun whenFindByUsername_thenThrowUserNotFound()
    {
        // given
        val fakeUsername = "FakeUserName"
        // when, then
        assertThrows<UsernameNotFoundException> { this.userAuthenticationDetailsService.loadUserByUsername(fakeUsername) }
    }

    //endregion

    //region Convenience tools

    fun createUser(): User
    {
        var user = User()
        user.email = "henlo.fren@dog.go"
        user.password = "fren"
        user.username = "Henlo"
        return this.testEntityManager.persistAndFlush(user);
    }

    //endregion
}