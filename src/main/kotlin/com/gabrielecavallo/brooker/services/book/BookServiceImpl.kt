package com.gabrielecavallo.brooker.services.book

import com.gabrielecavallo.brooker.common.stringToObjectId
import com.gabrielecavallo.brooker.domain.dto.BookCreateDTO
import com.gabrielecavallo.brooker.domain.entities.Book
import com.gabrielecavallo.brooker.exceptions.InvalidIdException
import com.gabrielecavallo.brooker.repositories.BookRepository
import com.gabrielecavallo.brooker.services.publisher.PublisherService
import com.gabrielecavallo.brooker.services.vendor.VendorService
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(
    val bookRepository: BookRepository,
    val mongoTemplate: MongoTemplate,
    val publisherService: PublisherService,
    val vendorService: VendorService
) : BookService {
    override fun findAll(filter: BookFilter): List<Book> =
        filter.filter(mongoTemplate)

    override fun save(data: Book): Book =
        bookRepository.save(data)

    fun save(dto: BookCreateDTO): Book =
        bookRepository.save(constructFromDTO(dto))

    override fun saveAll(data: List<Book>): List<Book> =
        bookRepository.saveAll(data)

    override fun findById(id: String): Book =
        bookRepository.findById(stringToObjectId(id)).orElseThrow {
            InvalidIdException(id)
        }

    override fun findAll(): List<Book> =
        bookRepository.findAll()

    override fun count(): Long =
        bookRepository.count()

    override fun removeById(id: String): Book {
        val data = findById(id)

        bookRepository.deleteById(stringToObjectId(id))
        return data
    }

    override fun removeAll() =
        bookRepository.deleteAll()

    override fun update(id: String, data: Book): Book {
        val idObj = stringToObjectId(id)

        data.id = id

        bookRepository.deleteById(idObj)
        bookRepository.save(data)

        return data
    }

    override fun saveAllDTO(data: List<BookCreateDTO>): List<Book> =
        bookRepository.saveAll(data.map { constructFromDTO(it) })

    override fun constructFromDTO(dto: BookCreateDTO) = Book(
        dto.title,
        dto.description,
        dto.plot,
        dto.bodyKey,
        dto.publishers.map { publisherService.findById(it) },
        vendorService.findById(dto.vendorId),
        dto.price,
        dto.lastModifiedDate,
        dto.publishedDate
    )
}