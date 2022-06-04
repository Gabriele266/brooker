package com.gabrielecavallo.brooker.services.book

import com.gabrielecavallo.brooker.domain.dto.BookCreateDTO
import com.gabrielecavallo.brooker.domain.entities.Book
import com.gabrielecavallo.brooker.services.common.CRUDService

interface BookService : CRUDService<Book, String> {
    /**
     * Find all the books
     */
    fun findAll(filter: BookFilter): List<Book>

    /**
     * Construct a book from the given DTO object
     * for creation
     */
    fun constructFromDTO(dto: BookCreateDTO): Book

    /**
     * Save all the books from the dto
     */
    fun saveAllDTO(data: List<BookCreateDTO>): List<Book>
}