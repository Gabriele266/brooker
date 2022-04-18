package com.gabrielecavallo.brooker.services.publisher

import com.gabrielecavallo.brooker.domain.entities.Publisher
import com.gabrielecavallo.brooker.services.common.NamedMongoFilter
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.findAll

class PublisherFilter(
    firstName: String? = null,
    lastName: String? = null,
    val averageRating: Float? = null,
    val language: String? = null,
    val tags: List<String> = listOf()
) : NamedMongoFilter<Publisher>(firstName, lastName) {
    override fun filter(mongoTemplate: MongoTemplate): List<Publisher> {
        return mongoTemplate.findAll()
    }

    fun hasAverageRating() = averageRating != null
    fun hasLanguage() = language != null
    fun hasTags() = tags.isNotEmpty()
}