package com.jissay.unlocked.repositories

import com.jissay.unlocked.entities.AccountRoleTestTools
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class AccountRoleRepositoryIntegrationTest
{
    @Autowired
    private lateinit var accountRoleRepository: AccountRoleRepository

    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    private val accountRoleTestTools = AccountRoleTestTools()

    //region Tests

    @Test
    fun `account role should be found and equal`()
    {
        //given
        this.testEntityManager.persistAndFlush(this.accountRoleTestTools.accountRole1)

        // when
        val userRoleFound = this.accountRoleRepository.findByName(this.accountRoleTestTools.accountRole1.name)

        // then
        this.accountRoleTestTools.assertRolesAreEquals(this.accountRoleTestTools.accountRole1, userRoleFound)
    }

    //endregion
}
