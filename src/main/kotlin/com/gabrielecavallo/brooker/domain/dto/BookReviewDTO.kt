package com.gabrielecavallo.brooker.domain.dto

data class BookReviewDTO(
    val bookId: String,
    val title: String,
    val body: String,
    val stars: Int,
    val userId: String,
)