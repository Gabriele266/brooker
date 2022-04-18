package com.gabrielecavallo.brooker.services.publisher

import com.gabrielecavallo.brooker.domain.entities.Publisher
import com.gabrielecavallo.brooker.services.common.CRUDService

interface PublisherService: CRUDService<Publisher, String> {
    /**
     * Find all publishers filtering them
     */
    fun findAll(filter: PublisherFilter): List<Publisher>

    /**
     * Find all the publishers of language
     */
    fun findAllOfLanguage(language: String): List<Publisher>
}