package com.gabrielecavallo.brooker.services.publisher

import com.gabrielecavallo.brooker.common.stringToObjectId
import com.gabrielecavallo.brooker.domain.entities.Publisher
import com.gabrielecavallo.brooker.exceptions.InvalidIdException
import com.gabrielecavallo.brooker.repositories.PublisherRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

@Service
class PublisherServiceImpl(
    val publisherRepository: PublisherRepository,
    val mongoTemplate: MongoTemplate
) : PublisherService {
    override fun save(data: Publisher): Publisher =
        publisherRepository.save(data)

    override fun saveAll(data: List<Publisher>): List<Publisher> =
        publisherRepository.saveAll(data)

    override fun findById(id: String): Publisher =
        publisherRepository.findById(stringToObjectId(id)).orElseThrow {
            InvalidIdException(id)
        }

    override fun findAll(): List<Publisher> =
        publisherRepository.findAll()

    override fun count(): Long =
        publisherRepository.count()

    override fun removeById(id: String): Publisher {
        val data = findById(id)

        publisherRepository.deleteById(stringToObjectId(id))
        return data
    }

    override fun removeAll() =
        publisherRepository.deleteAll()

    override fun update(id: String, data: Publisher): Publisher {
        removeById(id)
        return save(data)
    }

    override fun findAll(filter: PublisherFilter): List<Publisher> =
        filter.filter(mongoTemplate)

    override fun findAllOfLanguage(language: String): List<Publisher> =
        findAll(PublisherFilter(language = language))
}