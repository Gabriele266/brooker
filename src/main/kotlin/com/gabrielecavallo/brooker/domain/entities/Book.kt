package com.gabrielecavallo.brooker.domain.entities

import com.gabrielecavallo.brooker.domain.shared.WithId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.time.LocalDateTime

@Document
class Book(
    var title: String,
    var description: String,
    var plot: String,
    var bodyKey: String,
    @DocumentReference
    var publishers: List<Publisher>,
    @DocumentReference
    var vendor: Vendor,
    var price: Int,
    var lastModifiedDate: LocalDateTime,
    var publishedDate: LocalDateTime
) : WithId() {
    override fun toString(): String {
        return "Book(title='$title', description='$description', plot='$plot', bodyKey='$bodyKey', publishers=$publishers, vendor=$vendor, price=$price, lastModifiedDate=$lastModifiedDate, publishedDate=$publishedDate)"
    }
}