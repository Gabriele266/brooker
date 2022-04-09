package com.gabrielecavallo.brooker.repositories

import com.gabrielecavallo.brooker.domain.entities.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: MongoRepository<User, ObjectId> {
}