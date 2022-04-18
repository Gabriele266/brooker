package com.gabrielecavallo.brooker.repositories

import com.gabrielecavallo.brooker.domain.entities.BookOrder
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface BookOrderRepository: MongoRepository<BookOrder, ObjectId> {
    override fun <S : BookOrder?> save(entity: S): S
}