package com.gabrielecavallo.brooker.web.restful

import com.gabrielecavallo.brooker.domain.entities.Publisher
import com.gabrielecavallo.brooker.services.publisher.PublisherFilter
import com.gabrielecavallo.brooker.services.publisher.PublisherService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/publishers")
class PublisherController(
    val publisherService: PublisherService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllPublishers(
        @RequestParam firstName: String?,
        @RequestParam lastName: String?,
        @RequestParam averageRating: Float?,
        @RequestParam language: String?,
        @RequestParam(defaultValue = "") tags: List<String>
    ) = publisherService.findAll(PublisherFilter(firstName, lastName, averageRating, language, tags))

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postPublishers(@RequestBody publishers: List<Publisher>) =
        publisherService.saveAll(publishers)

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getPublisherById(@PathVariable id: String) =
        publisherService.findById(id)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updatePublisherById(@PathVariable id: String, @RequestBody data: Publisher) =
        publisherService.update(id, data)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun removePublisherById(@PathVariable id: String) =
        publisherService.removeById(id)
}