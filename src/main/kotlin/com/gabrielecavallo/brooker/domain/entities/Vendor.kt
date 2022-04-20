package com.gabrielecavallo.brooker.domain.entities

import com.gabrielecavallo.brooker.domain.shared.WithName
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Email

@Document
class Vendor(
    firstName: String,
    lastName: String,
    @Email(message = "Provide a valid email")
    var email: String,
    var tel: String,
    var address: String,
    var country: String,
): WithName(firstName, lastName) {
    override fun toString(): String {
        return "Vendor(email='$email', tel='$tel', address='$address', country='$country')"
    }
}