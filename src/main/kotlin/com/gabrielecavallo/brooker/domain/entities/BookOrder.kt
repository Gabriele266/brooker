package com.gabrielecavallo.brooker.domain.entities

import com.gabrielecavallo.brooker.domain.shared.WithId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.time.LocalDateTime

@Document
class BookOrder(
    @DocumentReference
    var user: User,
    @DocumentReference
    var book: Book,
    var date: LocalDateTime
) : WithId() {
    override fun toString(): String {
        return "BookOrder(user=$user, book=$book, date=$date)"
    }
}