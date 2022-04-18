package com.gabrielecavallo.brooker.services.common

abstract class NamedMongoFilter<T>(
    var firstName: String? = null,
    var lastName: String? = null
) : MongoFilter<T> {

    fun hasLastName() = lastName != null
    fun hasFirstName() = firstName != null
}