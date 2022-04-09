package com.gabrielecavallo.brooker.repositories

import com.gabrielecavallo.brooker.domain.entities.Vendor
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface VendorRepository : MongoRepository<Vendor, ObjectId> {
}