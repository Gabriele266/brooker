package com.gabrielecavallo.brooker.web

import com.gabrielecavallo.brooker.domain.entities.User
import com.gabrielecavallo.brooker.services.user.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class UserControllerTest @Autowired constructor(
    val restTemplate: TestRestTemplate,
    val userService: UserService,
) {
    @LocalServerPort
    var localServerPort: Int = 0

    @DisplayName("Test get all users")
    @Test
    fun testFindAll() {
        val result = restTemplate.getForEntity(endp("/users"), Array<User>::class.java)
        assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(result.body?.size).isGreaterThan(0)
    }

    @DisplayName("Test get user by id")
    @Test
    fun testGetById() {
        assertThat(
            restTemplate.getForEntity(
                endp("/users/55"),
                String::class.java,
            ).statusCode
        ).isEqualTo(HttpStatus.BAD_REQUEST)

        val validUser = userService.findAll().random()
        assertThat(
            restTemplate.getForEntity(
                endp("/users/${validUser.id}"),
                String::class.java,
            ).statusCode
        ).isEqualTo(HttpStatus.OK)
    }

    fun endp(name: String) = "http://localhost:$localServerPort$name"
}