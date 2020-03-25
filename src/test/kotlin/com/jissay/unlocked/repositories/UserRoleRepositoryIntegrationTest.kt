package com.jissay.unlocked.repositories

import com.jissay.unlocked.entities.UserRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner
import javax.management.relation.Role

@RunWith(SpringRunner::class)
@DataJpaTest
class UserRoleRepositoryIntegrationTest
{
    @Autowired
    private lateinit var userRoleRepository: UserRoleRepository

    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    //region Tests

    @Test
    fun whenFindByName_thenReturnRole()
    {
        //given
        val userRole = this.createRole()
        // when
        val userRoleFound = this.userRoleRepository.findByName(userRole.name)
        // then
        this.assertRolesAreEquals(userRole, userRoleFound)
    }

    //endregion

    //region Assertions

    fun assertRolesAreEquals(role1: UserRole, role2: UserRole)
    {
        assertThat(role1.id).isEqualTo(role2.id)
        assertThat(role1.name).isEqualTo(role2.name)
    }

    //endregion

    //region Convenience tools

    fun createRole(): UserRole
    {
        var role = UserRole("simpleRole")
        return this.testEntityManager.persistAndFlush(role)
    }
    //endregion
}
