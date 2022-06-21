package com.gabrielecavallo.brooker.web.restful

import com.gabrielecavallo.brooker.domain.dto.BookReviewDTO
import com.gabrielecavallo.brooker.services.review.ReviewService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookReviewController(
    val reviewService: ReviewService
) {
    @GetMapping("/{id}/reviews")
    fun getBookReviews(@PathVariable id: String) =
        reviewService.findForBook(id)

    @PostMapping("/{id}/reviews")
    fun addReviewsToBook(@PathVariable id: String, @RequestBody reviews: List<BookReviewDTO>) =
        reviewService.saveAllDto(reviews)

    @GetMapping("/reviews/{id}")
    fun getReviewById(@PathVariable id: String) =
        reviewService.findById(id)
}