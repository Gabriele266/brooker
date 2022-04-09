package com.gabrielecavallo.brooker.domain.entities

import com.gabrielecavallo.brooker.domain.shared.WithId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference

@Document
class BookReview(
    @DocumentReference
    var book: Book,
    var title: String,
    var body: String,
    var stars: Int
) : WithId() {
    override fun toString(): String {
        return "BookReview(book=$book, title='$title', body='$body', stars=$stars)"
    }
}