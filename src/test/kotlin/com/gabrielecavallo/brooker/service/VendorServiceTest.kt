package com.gabrielecavallo.brooker.service

import com.gabrielecavallo.brooker.domain.entities.Vendor
import com.gabrielecavallo.brooker.exceptions.InvalidIdException
import com.gabrielecavallo.brooker.services.vendor.VendorFilter
import com.gabrielecavallo.brooker.services.vendor.VendorService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
class VendorServiceTest @Autowired constructor(
    val vendorService: VendorService
) {
    val persistedData = listOf(
        Vendor(
            "Gabriele",
            "Cavallo",
            "gabri.cabal@gmail.com",
            "3317375441",
            "Via Navona",
            "IT"
        ),
        Vendor(
            "Zanichelli",
            "John",
            "john.zanichelli@gmail.com",
            "12345678910",
            "New York 2",
            "IT"
        ),
        Vendor(
            "Rizzoli",
            "Education",
            "rizzoli.education@rixed.com",
            "3317375441",
            "Via Rizzoli",
            "IT"
        ),
    )

    fun seedData() {
        vendorService.removeAll()
        vendorService.saveAll(persistedData)
    }

    init {
        seedData()
    }

    @DisplayName("Test update by id")
    @Test
    fun testUpdate() {
        val toUpdate = vendorService.findAll().random()
        toUpdate.email = "test.email@mail.it"
        toUpdate.firstName = "TestFirstName"

        val count = vendorService.count()
        val result = vendorService.update(toUpdate.id, toUpdate)
        val queryInfo = vendorService.findById(result.id)
        assertThat(queryInfo.email).isEqualTo(toUpdate.email)
        assertThat(queryInfo.firstName).isEqualTo(toUpdate.firstName)
        assertThat(count).isEqualTo(vendorService.count())
    }

    @DisplayName("Test find all")
    @Test
    fun testFindAll() {
        assertThat(vendorService.findAll()).isNotEmpty.isNotNull
    }

    @DisplayName("Test find by id")
    @Test
    fun testFindById() {
        assertThrows<InvalidIdException> {
            vendorService.findById("55")
        }
    }

    @DisplayName("Test remove by id")
    @Test
    fun testRemoveById() {
        val objToRemove = vendorService.findAll().random().id

        val count = vendorService.count()
        assertThat(vendorService.removeById(objToRemove)).isNotNull.hasFieldOrProperty("firstName")
        assertThat(vendorService.count()).isLessThan(count)
    }

    @DisplayName("Test remove by id throw exception")
    @Test
    fun testRemoveException() {
        assertThrows<InvalidIdException> {
            vendorService.removeById("55")
        }
    }

    @DisplayName("Test filtering")
    @Test
    fun testFiltering() {
        val filter = VendorFilter(
            country = "IT",
            address = "Via",
        )

        val results = vendorService.findAll(filter)

        assertThat(results).isNotEmpty
        results.forEach {
            assertThat(it.country).isEqualTo("IT")
            assertThat(it.address).contains("Via")
        }
    }
}