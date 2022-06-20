package com.gabrielecavallo.brooker.web.restful

import com.gabrielecavallo.brooker.domain.dto.BookCreateDTO
import com.gabrielecavallo.brooker.services.book.BookDownloadFormat
import com.gabrielecavallo.brooker.services.book.BookFilter
import com.gabrielecavallo.brooker.services.book.BookService
import com.gabrielecavallo.brooker.services.pdf.PdfService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RequestMapping("/books")
@RestController
class BookController(
    val bookService: BookService,
    val pdfService: PdfService
) {
    @GetMapping
    fun getBooks(
        @RequestParam title: String?,
        @RequestParam description: String?,
        @RequestParam plot: String?,
        @RequestParam publishedBy: String?,
        @RequestParam vendorId: String?,
        @RequestParam priceGreatherThan: Int?,
        @RequestParam priceLowerThan: Int?,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam modifiedBefore: LocalDateTime?,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam modifiedAfter: LocalDateTime?,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam publishedAfter: LocalDateTime?,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam publishedBefore: LocalDateTime?,
    ) = bookService.findAll(
        BookFilter(
            title,
            description,
            plot,
            publishedBy,
            vendorId,
            priceGreatherThan,
            priceLowerThan,
            modifiedBefore,
            modifiedAfter,
            publishedBefore,
            publishedAfter
        )
    )

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