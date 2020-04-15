package com.jissay.unlocked.entities

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.TestInstance

/* See https://phauer.com/2018/best-practices-unit-testing-kotlin/#avoid-static-and-reuse-the-test-class-instance */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountRoleTestTools
{
    val accountRole1 = AccountRole("role1")

    //region Assertions

    fun assertRolesAreEquals(role1: AccountRole, role2: AccountRole)
    {
        Assertions.assertThat(role1.id).isEqualTo(role2.id)
        Assertions.assertThat(role1.name).isEqualTo(role2.name)
    }

    //endregion
}