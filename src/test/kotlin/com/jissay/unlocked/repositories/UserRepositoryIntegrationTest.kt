package com.jissay.unlocked.repositories

import com.jissay.unlocked.entities.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class UserRepositoryIntegrationTest
{
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    @Test
    fun whenFindByEmail_thenReturnUser()
    {
        // given
        val userSaved = this.createUser()
        // when
        val userFound = this.userRepository.findByEmail(userSaved.email)
        // then
        this.assertUsersAreEquals(userSaved, userFound)
    }

    @Test
    fun whenFindByUsername_thenReturnUser()
    {
        // given
        val userSaved = this.createUser()
        // when
        val userFound = this.userRepository.findByAccountName(userSaved.username)
        // then
        this.assertUsersAreEquals(userSaved, userFound)
    }

    //region Assertions

    fun assertUsersAreEquals(user1: User, user2: User?)
    {
        assertThat(user1.id).isEqualTo(user2?.id)
        assertThat(user1.email).isEqualTo(user2?.email)
        assertThat(user1.password).isEqualTo(user2?.password)
        assertThat(user1.username).isEqualTo(user2?.username)
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