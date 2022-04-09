package com.gabrielecavallo.brooker.domain.entities

import com.gabrielecavallo.brooker.domain.shared.WithId
import com.gabrielecavallo.brooker.domain.shared.WithName
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Publisher(
    firstName: String,
    lastName: String,
    var story: String,
    var averageRating: Float,
    var language: String,
    var tags: List<String>
) : WithName(firstName, lastName) {
    override fun toString(): String {
        return "Publisher(story='$story', averageRating=$averageRating, language='$language', tags=$tags)"
    }
}