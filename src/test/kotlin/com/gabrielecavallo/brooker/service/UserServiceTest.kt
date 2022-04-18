package com.gabrielecavallo.brooker.service

import com.gabrielecavallo.brooker.common.randomInt
import com.gabrielecavallo.brooker.common.randomLong
import com.gabrielecavallo.brooker.domain.entities.User
import com.gabrielecavallo.brooker.services.user.UserFilter
import com.gabrielecavallo.brooker.services.user.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest @Autowired constructor(
    val userService: UserService,
) {

    val persistedUsers = listOf(
        User(
            "Gabriele",
            "Cavallo",
            "mimmo.cabal@gmail.com",
            "Via Cesare Vinaj 2",
            LocalDateTime.now(),
            "IT"
        ), User(
            "Giovanni",
            "Beraudo",
            "giovanni.bera@gmail.com",
            "Via Navona",
            LocalDateTime.now(),
            "IT"
        ), User(
            "Piero",
            "Beraudo",
            "giovanni.bera@gmail.com",
            "Via Navona",
            LocalDateTime.now(),
            "EN"
        ), User(
            "Hola",
            "Senorito",
            "hola.senorito@mail.com",
            "Via Madrid 21",
            LocalDateTime.now(),
            "ES"
        )
    )

    init {
        seedData()
    }

    /**
     * Seed all the data
     */
    fun seedData() {
        userService.removeAll()

        userService.saveAll(persistedUsers)
    }


    @Test
    fun sampleTest() {
        assertThat(userService.findAll()).isNotNull
    }

    @DisplayName("Filtering test")
    @Test
    fun testFiltering() {

        val result = userService.findAll(UserFilter(lastName = "Beraudo", firstName = "Giovanni", country = "IT"))
        assertThat(result).isNotNull

        result.forEach {
            assertThat(it.lastName).isEqualTo("Beraudo")
            assertThat(it.firstName).isEqualTo("Giovanni")
            assertThat(it.country).isEqualTo("IT")
        }
    }

    @DisplayName("Update user test")
    @Test
    fun testUpdate() {
        val user = userService.findAll().first()

        val updateUser = User(
            user.firstName,
            "TestLastNameUpdate",
            user.email,
            "Via Navarra Test",
            user.birthDate,
            user.country
        )

        val updatedUserId = userService.update(user.id, updateUser).id

        val updatedUser = userService.findById(updatedUserId)
        assertThat(updatedUser.firstName).isEqualTo(user.firstName)
        assertThat(updatedUser.lastName).isEqualTo("TestLastNameUpdate")
        assertThat(updatedUser.address).isEqualTo("Via Navarra Test")
    }

    @DisplayName("Delete by id test")
    @Test
    fun testDeleteById() {
        val originalUser = userService.findAll()[randomLong(0L, userService.count()).toInt()]

        val removeResult = userService.removeById(originalUser.id)
        assertThat(removeResult.id).isEqualTo(originalUser.id)
        assertThat(removeResult.address).isEqualTo(originalUser.address)
    }

    @DisplayName("Find all test")
    @Test
    fun testFindAll() {
        val allUsers = userService.findAll()
        assertThat(allUsers).isNotNull
        assertThat(allUsers.size).isGreaterThan(-1)
    }
}