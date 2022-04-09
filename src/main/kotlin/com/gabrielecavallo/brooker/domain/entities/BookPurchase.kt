package com.gabrielecavallo.brooker.domain.entities

import com.gabrielecavallo.brooker.domain.shared.WithId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.time.LocalDateTime

@Document
class BookPurchase(
    @DocumentReference
    var user: User,
    @DocumentReference
    var book: Book,
    var purchaseDate: LocalDateTime
) : WithId() {
    override fun toString(): String {
        return "BookPurchase(user=$user, book=$book, purchaseDate=$purchaseDate)"
    }
}