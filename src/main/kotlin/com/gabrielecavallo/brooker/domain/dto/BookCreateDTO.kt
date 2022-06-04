package com.gabrielecavallo.brooker.domain.dto

import org.springframework.data.mongodb.core.mapping.DocumentReference
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class BookCreateDTO(
    var title: String,
    var description: String,
    var plot: String,
    var bodyKey: String,
    @DocumentReference
    var publishers: List<String>,
    @DocumentReference
    var vendorId: String,
    var price: Int,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    var lastModifiedDate: LocalDateTime,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    var publishedDate: LocalDateTime
) {
}