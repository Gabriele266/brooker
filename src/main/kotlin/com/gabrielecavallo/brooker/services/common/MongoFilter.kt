package com.gabrielecavallo.brooker.services.common

import org.springframework.data.mongodb.core.MongoTemplate

interface MongoFilter<T> {
    fun filter(mongoTemplate: MongoTemplate): List<T>
}