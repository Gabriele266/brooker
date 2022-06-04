package com.gabrielecavallo.brooker.web.graphql

import com.netflix.graphql.dgs.DgsQueryExecutor
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserDataFetcherTest {
    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @DisplayName("Get all users")
    @Test
    fun testGetAllUsers() {
        val userNames = dgsQueryExecutor.executeAndExtractJsonPath<List<String>>(
            """
            query {
                users {
                    firstName
                }
            }
        """.trimIndent(), "*"
        )

        assertThat(userNames.size).isGreaterThan(0)
    }
}