package com.gabrielecavallo.brooker.services.book

import com.gabrielecavallo.brooker.common.stringToObjectId
import com.gabrielecavallo.brooker.domain.entities.Book
import com.gabrielecavallo.brooker.services.common.MongoFilter
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import java.time.LocalDateTime

class BookFilter(
    val title: String?,
    val description: String?,
    val plot: String?,
    val publishedBy: String?,
    val vendorId: String?,
    val priceGreatherThan: Int?,
    val priceLowerThan: Int?,
    val modifiedBefore: LocalDateTime?,
    val modifiedAfter: LocalDateTime?,
    val publishedBefore: LocalDateTime?,
    val publishedAfter: LocalDateTime?
) : MongoFilter<Book> {
    override fun filter(mongoTemplate: MongoTemplate): List<Book> {
        val query = Query()

        if (description != null)
            query.addCriteria(Criteria.where("description").regex("/$description/"))

        if (plot != null)
            query.addCriteria(Criteria.where("plot").regex("/$plot/"))

        if (priceGreatherThan != null)
            query.addCriteria(Criteria.where("price").gt(priceGreatherThan))

        if (vendorId != null)
            query.addCriteria(Criteria.where("vendor").isEqualTo(stringToObjectId(vendorId)))

        if (priceLowerThan != null)
            query.addCriteria(Criteria.where("price").lt(priceLowerThan))

        var initial = mongoTemplate.find(query, Book::class.java)

        if (title != null)
            initial = initial.filter { it.title.contains(title, true) }

        if (modifiedBefore != null)
            initial = initial.filter {
                it.lastModifiedDate.isBefore(modifiedBefore)
            }

        if (modifiedAfter != null)
            initial = initial.filter {
                it.lastModifiedDate.isAfter(modifiedAfter)
            }

        if (publishedBefore != null)
            initial = initial.filter { it.publishedDate.isBefore(publishedBefore) }

        if (publishedAfter != null)
            initial = initial.filter { it.publishedDate.isAfter(publishedAfter) }

        if (publishedBy != null)
            initial = initial.filter { it.publishers.any { it.firstName == publishedBy } }

        return initial
    }
}