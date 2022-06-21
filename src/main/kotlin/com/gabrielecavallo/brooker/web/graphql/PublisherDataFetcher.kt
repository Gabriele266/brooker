package com.gabrielecavallo.brooker.web.graphql

import com.gabrielecavallo.brooker.services.publisher.PublisherService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class PublisherDataFetcher(
    val publisherService: PublisherService
) {
    @DgsQuery
    fun publishers() = publisherService.findAll()

    @DgsQuery
    fun publisher(@InputArgument id: String) = publisherService.findById(id)
}