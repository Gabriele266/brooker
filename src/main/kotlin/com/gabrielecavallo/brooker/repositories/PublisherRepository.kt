package com.gabrielecavallo.brooker.repositories

import com.gabrielecavallo.brooker.domain.entities.Publisher
import org.bson.types.ObjectId
import org.springframework.data.domain.Example
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PublisherRepository : MongoRepository<Publisher, ObjectId> {
    override fun <S : Publisher?> save(entity: S): S
    override fun <S : Publisher?> findOne(example: Example<S>): Optional<S>
    override fun findById(id: ObjectId): Optional<Publisher>
}