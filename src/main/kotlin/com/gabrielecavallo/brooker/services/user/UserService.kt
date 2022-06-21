package com.gabrielecavallo.brooker.services.user

import com.gabrielecavallo.brooker.domain.entities.User
import com.gabrielecavallo.brooker.services.common.CRUDService

interface UserService: CRUDService<User, String> {
    /**
     * Find all the users with the given first name
     */
    fun findAllWithName(firstName: String): List<User>

    /**
     * Find all the users with the given last name
     */
    fun findAllWithLastName(lastName: String): List<User>

    /**
     * Find all the users of this country
     */
    fun findAllOfCountry(country: String): List<User>

    /**
     * Find all users filtered
     */
    fun findAll(filter: UserFilter): List<User>

    /**
     * Check if there is a user with the given id
     */
    fun hasWithId(id: String): Boolean
}