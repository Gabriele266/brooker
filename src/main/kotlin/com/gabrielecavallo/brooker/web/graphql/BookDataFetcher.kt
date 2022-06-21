package com.gabrielecavallo.brooker.web.graphql

import com.gabrielecavallo.brooker.domain.entities.Book
import com.gabrielecavallo.brooker.domain.entities.BookReview
import com.gabrielecavallo.brooker.services.book.BookService
import com.gabrielecavallo.brooker.services.review.ReviewService
import com.netflix.graphql.dgs.*

@DgsComponent
class BookDataFetcher(
    val bookService: BookService,
    val reviewService: ReviewService
) {
    @DgsQuery(field = "books")
    fun getBooks() =
        bookService.findAll()

    @DgsData(parentType = "Book", field = "reviews")
    fun getReviews(env: DgsDataFetchingEnvironment): List<BookReview> {
        val book = env.getSource<Book>()
        return reviewService.findForBook(book.id)
    }

    @DgsQuery(field = "book")
    fun getBookById(@InputArgument id: String) =
        bookService.findById(id)
}