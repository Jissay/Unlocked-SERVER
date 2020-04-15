package com.jissay.unlocked.repositories

import com.jissay.unlocked.entities.AccountTestTools
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class AccountRepositoryIntegrationTest
{
    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    private val accountTestTools = AccountTestTools()

    //region Tests

    @Test
    fun `account should be found and equal`()
    {
        // given
        this.testEntityManager.persistAndFlush(this.accountTestTools.account1)

        // when
        val userFound = this.accountRepository.findByUsername(this.accountTestTools.account1.username)

        // then
        this.accountTestTools.assertAccountsAreEquals(this.accountTestTools.account1, userFound)
    }

    //endregion
}