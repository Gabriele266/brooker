package com.gabrielecavallo.brooker.services.publisher

import com.gabrielecavallo.brooker.domain.entities.Publisher
import com.gabrielecavallo.brooker.services.common.NamedMongoFilter
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo

class PublisherFilter(
    firstName: String? = null,
    lastName: String? = null,
    val averageRating: Float? = null,
    val language: String? = null,
    val tags: List<String> = listOf()
) : NamedMongoFilter<Publisher>(firstName, lastName) {
    override fun filter(mongoTemplate: MongoTemplate): List<Publisher> {
        val query = Query()

        if (hasFirstName())
            query.addCriteria(Criteria.where("firstName").`is`(firstName))
        if (hasLastName())
            query.addCriteria(Criteria.where("lastName").`is`(lastName))
        if (hasAverageRating())
            query.addCriteria(Criteria.where("averageRating").gt(averageRating as Float))
        if (hasLanguage())
            query.addCriteria(Criteria.where("language").isEqualTo(language))
        if (hasTags())
            query.addCriteria(Criteria.where("tags").all(tags))

        return mongoTemplate.find(query)
    }

    fun hasAverageRating() = averageRating != null
    fun hasLanguage() = language != null
    fun hasTags() = tags.isNotEmpty()
}