package com.gabrielecavallo.brooker.web.graphql.input

import com.gabrielecavallo.brooker.domain.entities.User
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class UserInput(
    var firstName: String,
    var lastName: String,
    var email: String,
    var address: String,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    var birthDate: LocalDateTime,
    var country: String,
) {
    fun toUser(): User =
        User(firstName, lastName, email, address, birthDate, country)
}