package com.gabrielecavallo.brooker.domain.entities

import com.gabrielecavallo.brooker.domain.shared.WithId
import com.gabrielecavallo.brooker.domain.shared.WithName
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class User(
    firstName: String,
    lastName: String,
    var email: String,
    var address: String,
    var birthDate: LocalDateTime,
    var country: String,
) : WithName(firstName, lastName) {
    override fun toString(): String {
        return "User(email='$email', address='$address', birthDate=$birthDate, country='$country')"
    }
}