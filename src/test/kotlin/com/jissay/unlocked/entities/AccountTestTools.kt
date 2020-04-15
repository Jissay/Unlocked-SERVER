package com.jissay.unlocked.entities

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.TestInstance

/* See https://phauer.com/2018/best-practices-unit-testing-kotlin/#avoid-static-and-reuse-the-test-class-instance */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountTestTools
{
    val account1: Account = Account("hello@unlocked.com", "hello_account", "hello_password")

    //region Assertions

    fun assertAccountsAreEquals(user1: Account, user2: Account?)
    {
        Assertions.assertThat(user1.id).isEqualTo(user2?.id)
        Assertions.assertThat(user1.email).isEqualTo(user2?.email)
        Assertions.assertThat(user1.password).isEqualTo(user2?.password)
        Assertions.assertThat(user1.username).isEqualTo(user2?.username)
    }

    //endregion
}