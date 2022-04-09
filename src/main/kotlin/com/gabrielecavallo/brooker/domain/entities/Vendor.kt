package com.gabrielecavallo.brooker.domain.entities

import com.gabrielecavallo.brooker.domain.shared.WithId
import com.gabrielecavallo.brooker.domain.shared.WithName
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Vendor(
    firstName: String,
    lastName: String,
    var email: String,
    var tel: String,
    var address: String,
    var country: String,
): WithName(firstName, lastName) {
    override fun toString(): String {
        return "Vendor(email='$email', tel='$tel', address='$address', country='$country')"
    }
}