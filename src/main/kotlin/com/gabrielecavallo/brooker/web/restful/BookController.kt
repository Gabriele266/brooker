package com.gabrielecavallo.brooker.web.restful

import com.gabrielecavallo.brooker.domain.dto.BookCreateDTO
import com.gabrielecavallo.brooker.services.book.BookDownloadFormat
import com.gabrielecavallo.brooker.services.book.BookService
import com.gabrielecavallo.brooker.services.pdf.PdfService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/books")
@RestController
class BookController(
    val bookService: BookService,
    val pdfService: PdfService
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

    @GetMapping("/download/{id}")
    fun downloadBookById(@PathVariable id: String, @RequestParam format: String?): ResponseEntity<String> {
        val bookInfo = getBookById(id)

        val ft = BookDownloadFormat.fromCode(format ?: BookDownloadFormat.PDF.code)
        val data = bookService.download(id, ft)

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${bookInfo.bodyKey}.${ft.code}\"")
            .body(data)
    }

    @DeleteMapping
    fun removeBooksById(@RequestBody ids: List<String>): String {
        ids.forEach { bookService.removeById(it) }

        return "true"
    }
}