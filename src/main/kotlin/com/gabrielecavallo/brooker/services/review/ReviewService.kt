package com.gabrielecavallo.brooker.services.review

import com.gabrielecavallo.brooker.domain.dto.BookReviewDTO
import com.gabrielecavallo.brooker.domain.entities.BookReview
import com.gabrielecavallo.brooker.services.common.CRUDService
import com.gabrielecavallo.brooker.validators.common.ValidatorException

/**
 * Review service
 */
interface ReviewService : CRUDService<BookReview, String> {
    /**
     * Find all the reviews of the user
     */
    fun findForUser(userId: String): List<BookReview>

    /**
     * Find all the reviews for a book
     */
    fun findForBook(bookId: String): List<BookReview>

    /**
     * Construct a review from its dto or throw exception
     */
    @kotlin.jvm.Throws(ValidatorException::class)
    fun construct(dto: BookReviewDTO): BookReview

    /**
     * Save a single book review dto
     */
    fun save(dto: BookReviewDTO): BookReview

    /**
     * Save all dto
     */
    fun saveAllDto(dtos: List<BookReviewDTO>): List<BookReview>
}