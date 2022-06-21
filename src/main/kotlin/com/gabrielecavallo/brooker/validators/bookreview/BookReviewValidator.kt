package com.gabrielecavallo.brooker.validators.bookreview

import com.gabrielecavallo.brooker.domain.dto.BookReviewDTO
import com.gabrielecavallo.brooker.services.book.BookService
import com.gabrielecavallo.brooker.services.user.UserService
import com.gabrielecavallo.brooker.validators.common.Validator
import com.gabrielecavallo.brooker.validators.common.ValidatorException
import org.springframework.stereotype.Service

@Service
class BookReviewValidator(
    val bookService: BookService,
    var userService: UserService
) : Validator<BookReviewDTO> {
    override fun validateOne(input: BookReviewDTO): Boolean {
        // Check book id
        if (!bookService.hasWithId(input.bookId)) return false
        if (!userService.hasWithId(input.userId)) return false
        if (input.stars > 5 || input.stars < 0) return false

        return true
    }

    override fun validateOrThrow(input: BookReviewDTO) {
        if (!bookService.hasWithId(input.bookId)) throw ValidatorException("Invalid book id")
        if (!userService.hasWithId(input.userId)) throw ValidatorException("Invalid user id")
        if (input.stars > 5 || input.stars < 0) throw ValidatorException("Invalid stars value. 0 < x < 5")
    }
}