package com.gabrielecavallo.brooker.repositories

import com.gabrielecavallo.brooker.domain.entities.Book
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: MongoRepository<Book, ObjectId> {
    override fun <S : Book?> save(entity: S): S
}