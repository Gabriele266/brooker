package com.gabrielecavallo.brooker.services.common

interface CRUDService<T, ID> {
    /**
     * Save a new entity
     */
    fun save(data: T): T

    /**
     * Save an entity list
     */
    fun saveAll(data: List<T>): List<T>

    /**
     * Find by id
     */
    fun findById(id: ID): T

    /**
     * Find all entities
     */
    fun findAll(): List<T>

    /**
     * Get the count
     */
    fun count(): Long

    /**
     * Remove one by id
     */
    fun removeById(id: ID): T

    /**
     * Remove all users
     */
    fun removeAll()

    /**
     * Update an existing entity
     */
    fun update(id: ID, data: T): T
}