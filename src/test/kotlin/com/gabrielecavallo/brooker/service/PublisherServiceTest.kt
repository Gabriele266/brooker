package com.gabrielecavallo.brooker.service

import com.gabrielecavallo.brooker.domain.entities.Publisher
import com.gabrielecavallo.brooker.services.publisher.PublisherService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
class PublisherServiceTest @Autowired constructor(
    val publisherService: PublisherService
) {
    val persistedData = listOf(
        Publisher(
            "Thomson",
            "Reuters",
            "My story is very amazing",
            4.5F,
            "EN",
            listOf("story", "narrative")
        ),
        Publisher(
            "MCGraw-Hill",
            "Education",
            "My story is not very amazing",
            3.4F,
            "EN",
            listOf("story", "narrative", "police")
        ),
        Publisher(
            "Pearson",
            "Place",
            "I publish very good books",
            5.0F,
            "EN",
            listOf("story", "narrative", "yellow")
        )
    )

    init {
        seedData()
    }

    fun seedData() {
        publisherService.removeAll()
        publisherService.saveAll(persistedData)
    }

    @DisplayName("Test find all")
    @Test
    fun testFindAll() {
        val allData = publisherService.findAll()
        assertThat(allData).isNotNull
    }

    @DisplayName("Test find by id")
    @Test
    fun testFindById() {
        val toU = publisherService.findAll().random()

        val detail = publisherService.findById(toU.id)

        assertThat(detail).isNotNull
        assertThat(detail).isEqualTo(toU)
    }

    @DisplayName("Test remove by id")
    @Test
    fun testRemoveById() {
        val toRemove = publisherService.findAll().random()

        val initCount = publisherService.count()
        assertThat(publisherService.removeById(toRemove.id)).isNotNull.isEqualTo(toRemove)
        assertThat(publisherService.count()).isLessThan(initCount)
    }

    @DisplayName("Test update by id")
    @Test
    fun testUpdateById() {
        val toUpdate = publisherService.findAll().random()
        toUpdate.apply {
            language = "IT"
            firstName = "TestFirstName"
        }

        val initSize = publisherService.count()
        val updateResult = publisherService.update(toUpdate.id, toUpdate)
        assertThat(updateResult).isNotNull
        assertThat(publisherService.count()).isEqualTo(initSize)
        assertThat(publisherService.findById(updateResult.id)).isNotNull.isEqualTo(toUpdate)
    }
}