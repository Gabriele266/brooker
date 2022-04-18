package com.gabrielecavallo.brooker.domain.entities

import com.gabrielecavallo.brooker.domain.shared.WithName
import org.jetbrains.annotations.NotNull
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

@Document
class User(
    firstName: String,
    lastName: String,
    @NotEmpty(message = "Please provide an email")
    @Email(message = "Email not valid")
    var email: String,
    var address: String,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    var birthDate: LocalDateTime,
    var country: String,
) : WithName(firstName, lastName) {
    override fun toString(): String {
        return "User(email='$email', address='$address', birthDate=$birthDate, country='$country')"
    }
}