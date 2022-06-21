package com.gabrielecavallo.brooker.services.book

import com.gabrielecavallo.brooker.common.stringIsObjectId
import com.gabrielecavallo.brooker.common.stringToObjectId
import com.gabrielecavallo.brooker.domain.dto.BookCreateDTO
import com.gabrielecavallo.brooker.domain.entities.Book
import com.gabrielecavallo.brooker.exceptions.InvalidIdException
import com.gabrielecavallo.brooker.repositories.BookRepository
import com.gabrielecavallo.brooker.services.pdf.PdfService
import com.gabrielecavallo.brooker.services.publisher.PublisherService
import com.gabrielecavallo.brooker.services.s3.S3Service
import com.gabrielecavallo.brooker.services.vendor.VendorService
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BookServiceImpl(
    val bookRepository: BookRepository,
    val mongoTemplate: MongoTemplate,
    val publisherService: PublisherService,
    val vendorService: VendorService,
    val s3Service: S3Service,
    val pdfService: PdfService
) : BookService {
    override fun findAll(filter: BookFilter): List<Book> =
        filter.filter(mongoTemplate)

    override fun save(data: Book): Book =
        bookRepository.save(data)

    fun save(dto: BookCreateDTO): Book {
        var book = constructFromDTO(dto)

        runBlocking {
            launch {
                s3Service.upload(dto.title, dto.htmlContent)
            }

            launch {
                bookRepository.save(book)
            }
        }
        // Upload to s3

        book.bodyKey = dto.title

        return book
    }

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

    override fun saveAllDTO(data: List<BookCreateDTO>): List<Book> {
        val booksData = data.map {
            val book = constructFromDTO(it)

            val key = formatBookS3Key(book)
            book.bodyKey = key

            book
        }

        // Concurrently upload and save to the repo
        runBlocking {
            launch {
                bookRepository.saveAll(booksData)
            }

            launch {
                for ((index, value) in data.withIndex()) {
                    s3Service.upload(booksData[index].bodyKey, value.htmlContent)
                }
            }
        }

        return booksData
    }

    override fun constructFromDTO(dto: BookCreateDTO) = Book(
        dto.title,
        dto.description,
        dto.plot,
        dto.htmlContent,
        dto.publishers.map { publisherService.findById(it) },
        vendorService.findById(dto.vendorId),
        dto.price,
        dto.lastModifiedDate,
        dto.publishedDate
    )

    private fun formatBookS3Key(book: Book) =
        "bk${book.title.filter { !it.isWhitespace() }.lowercase()}-${
            LocalDateTime.now().toString().filter { it != '.' && it != ':' && it != '-' }
        }"

    override fun download(id: String, format: BookDownloadFormat): String {
        val book = findById(id)

        val result = s3Service.download(book.bodyKey)
        val html = String(result)

        return when (format) {
            BookDownloadFormat.HTML -> html
            BookDownloadFormat.IMG -> html
            BookDownloadFormat.PDF -> pdfService.createFromHtml(html)
        }
    }

    override fun hasWithId(id: String): Boolean {
        if (stringIsObjectId(id)) {
            val result: Book? = bookRepository.findById(stringToObjectId(id)).orElse(null)

            return result != null
        }
        return false
    }

    override fun findWithVendor(id: String): List<Book> {
        val query = Query()
        query.addCriteria(Criteria.where("vendor").isEqualTo(id))

        return mongoTemplate.find(query, Book::class.java)
    }
}