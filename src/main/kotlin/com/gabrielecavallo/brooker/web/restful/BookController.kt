package com.gabrielecavallo.brooker.web.restful

import com.gabrielecavallo.brooker.domain.dto.BookCreateDTO
import com.gabrielecavallo.brooker.services.book.BookService
import org.springframework.web.bind.annotation.*

@RequestMapping("/books")
@RestController
class BookController(
    val bookService: BookService
) {
    @GetMapping
    fun getBooks() = bookService.findAll()

    @PostMapping
    fun saveBooks(@RequestBody books: List<BookCreateDTO>) =
        bookService.saveAllDTO(books)

    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: String) =
        bookService.findById(id)

    @DeleteMapping("/{id}")
    fun removeBookByid(@PathVariable id: String) =
        bookService.removeById(id)
}