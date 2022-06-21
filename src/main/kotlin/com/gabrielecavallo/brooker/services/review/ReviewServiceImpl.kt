package com.gabrielecavallo.brooker.services.review

import com.gabrielecavallo.brooker.common.stringToObjectId
import com.gabrielecavallo.brooker.domain.dto.BookReviewDTO
import com.gabrielecavallo.brooker.domain.entities.BookReview
import com.gabrielecavallo.brooker.exceptions.InvalidIdException
import com.gabrielecavallo.brooker.repositories.BookReviewRepository
import com.gabrielecavallo.brooker.services.book.BookService
import com.gabrielecavallo.brooker.services.user.UserService
import com.gabrielecavallo.brooker.validators.bookreview.BookReviewValidator
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReviewServiceImpl(
    val reviewRepository: BookReviewRepository,
    val mongoTemplate: MongoTemplate,
    val validator: BookReviewValidator,
    val bookService: BookService,
    val userService: UserService
) : ReviewService {
    override fun save(data: BookReview): BookReview = reviewRepository.save(data)

    override fun saveAll(data: List<BookReview>): List<BookReview> = reviewRepository.saveAll(data)

    override fun findById(id: String): BookReview =
        reviewRepository.findById(stringToObjectId(id)).orElseThrow { InvalidIdException(id) }

    override fun findAll(): List<BookReview> =
        reviewRepository.findAll()

    override fun count(): Long =
        reviewRepository.count()

    override fun removeById(id: String): BookReview {
        val data = findById(id)
        reviewRepository.deleteById(stringToObjectId(id))
        return data
    }

    override fun removeAll() =
        reviewRepository.deleteAll()

    override fun update(id: String, data: BookReview): BookReview {
        TODO("Not yet implemented")
    }

    override fun findForUser(userId: String): List<BookReview> {
        val query = Query()
        query.addCriteria(Criteria.where("user").isEqualTo(userId))

        return mongoTemplate.find(query, BookReview::class.java)
    }

    override fun findForBook(bookId: String): List<BookReview> {
        val query = Query()
        query.addCriteria(Criteria.where("book").isEqualTo(stringToObjectId(bookId)))

        return mongoTemplate.find(query, BookReview::class.java)
    }

    override fun construct(dto: BookReviewDTO): BookReview {
        validator.validateOrThrow(dto)

        return BookReview(
            book = bookService.findById(dto.bookId),
            user = userService.findById(dto.userId),
            body = dto.body,
            creationDate = LocalDateTime.now(),
            stars = dto.stars,
            title = dto.title
        )
    }

    override fun save(dto: BookReviewDTO): BookReview =
        reviewRepository.save(construct(dto))

    override fun saveAllDto(dtos: List<BookReviewDTO>): List<BookReview> =
        dtos.map {
            val review = construct(it)
            reviewRepository.save(review)
        }
}