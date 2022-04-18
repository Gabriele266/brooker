package com.gabrielecavallo.brooker.services.user

import com.gabrielecavallo.brooker.domain.entities.User
import com.gabrielecavallo.brooker.services.common.NamedMongoFilter
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import java.time.LocalDateTime

class UserFilter(
    firstName: String? = null,
    lastName: String? = null,
    val country: String? = null,
    val initialBirthDate: LocalDateTime? = null,
    val address: String? = null,
    val endBirthDate: LocalDateTime? = null
) : NamedMongoFilter<User>(firstName, lastName) {
    override fun filter(mongoTemplate: MongoTemplate): List<User> {
        val query = Query()

        if (hasFirstName())
            query.addCriteria(Criteria.where("firstName").isEqualTo(firstName))

        if (hasLastName())
            query.addCriteria(Criteria.where("lastName").isEqualTo(lastName))

        if (hasCountry())
            query.addCriteria(Criteria.where("country").isEqualTo(country))

        if (hasAddress())
            query.addCriteria(Criteria.where("address").regex("\\b${address}\\b"))

        var allResults = mongoTemplate.find(query, User::class.java)

        if (hasInitialBirthDate()) allResults.filter {
            it.birthDate.isAfter(initialBirthDate)
        }

        if (hasEndBirthDate()) allResults = allResults.filter {
            it.birthDate.isBefore(endBirthDate)
        }

        return allResults
    }

    fun hasCountry() = country != null
    fun hasInitialBirthDate() = initialBirthDate != null
    fun hasAddress() = address != null
    fun hasEndBirthDate() = endBirthDate != null
}