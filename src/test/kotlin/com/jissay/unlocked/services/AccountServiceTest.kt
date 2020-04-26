package com.jissay.unlocked.services

import com.jissay.unlocked.repositories.AccountRepository
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.security.core.userdetails.UsernameNotFoundException

// see https://stackoverflow.com/questions/23086932/cannot-instantiate-injectmocks-field-named-exception-with-java-class/23087583
// https://medium.com/@crsandeep/creating-and-testing-a-kotlin-restful-web-services-using-spring-boot-1a11aeda279e
@RunWith(MockitoJUnitRunner::class)
@DataJpaTest
class AccountServiceTest {
    @InjectMocks
    private lateinit var accountService: AccountService

    @Mock
    private lateinit var accountRepository: AccountRepository // Dependency for AccountService

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    //region Tests

    @Test
    fun `username should not be found`() {
        // given
        val fakeUsername = "FakeUserName"
        // when, then
        assertThrows<UsernameNotFoundException> { this.accountService.loadUserByUsername(fakeUsername) }
    }

    // endregion
}
