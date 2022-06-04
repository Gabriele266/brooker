package com.gabrielecavallo.brooker.domain.entities

import com.gabrielecavallo.brooker.domain.shared.WithId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.Transient

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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    var lastModifiedDate: LocalDateTime,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    var publishedDate: LocalDateTime,
) : WithId() {
    @Transient
    var published: Boolean? = null

    init {
        //published = publishedDate.isAfter(minDate())
    }

    override fun toString(): String {
        return "Book(title='$title', description='$description', plot='$plot', bodyKey='$bodyKey', publishers=$publishers, vendor=$vendor, price=$price, lastModifiedDate=$lastModifiedDate, publishedDate=$publishedDate)"
    }
}