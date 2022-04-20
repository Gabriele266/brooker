package com.gabrielecavallo.brooker.services.vendor

import com.gabrielecavallo.brooker.domain.entities.Vendor
import com.gabrielecavallo.brooker.services.common.NamedMongoFilter
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo

class VendorFilter(
    firstName: String? = null,
    lastName: String? = null,
    val country: String? = null,
    val address: String? = null
) : NamedMongoFilter<Vendor>(firstName, lastName) {
    override fun filter(mongoTemplate: MongoTemplate): List<Vendor> {
        val query = Query()

        if (hasFirstName())
            query.addCriteria(Criteria.where("firstName").isEqualTo(firstName))
        if (hasLastName())
            query.addCriteria(Criteria.where(("lastName")).isEqualTo(lastName))
        if (hasCountry())
            query.addCriteria(Criteria.where("country").isEqualTo(country))
        if (hasAddress())
            query.addCriteria(Criteria.where("address").regex("\\b$address\\b"))

        return mongoTemplate.find(query)
    }

    fun hasCountry() = country != null
    fun hasAddress() = address != null
}