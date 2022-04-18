package com.gabrielecavallo.brooker.service

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

    init {
        seedData()
    }

    /**
     * Seed all the data
     */
    fun seedData() {
        userService.removeAll()

        userService.save(
            User(
                "Gabriele",
                "Cavallo",
                "mimmo.cabal@gmail.com",
                "Via Cesare Vinaj 2",
                LocalDateTime.now(),
                "IT"
            )
        )

        userService.save(
            User(
                "Giovanni",
                "Beraudo",
                "giovanni.bera@gmail.com",
                "Via Navona",
                LocalDateTime.now(),
                "IT"
            )
        )

        userService.save(
            User(
                "Piero",
                "Beraudo",
                "giovanni.bera@gmail.com",
                "Via Navona",
                LocalDateTime.now(),
                "EN"
            )
        )

        userService.save(
            User(
                "Hola",
                "Senorito",
                "hola.senorito@mail.com",
                "Via Madrid 21",
                LocalDateTime.now(),
                "ES"
            )
        )
    }


    @Test
    fun sampleTest() {
        assertThat(userService.count()).isEqualTo(3)
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

    @DisplayName("Find all test")
    @Test
    fun testFindAll() {
        val allUsers = userService.findAll()
        assertThat(allUsers.size).isEqualTo(3)
        assertThat(allUsers.any {
            it.firstName == "Giovanni"
        }).isTrue
    }
}