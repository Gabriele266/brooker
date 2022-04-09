package com.gabrielecavallo.brooker.domain.shared

import org.springframework.data.mongodb.core.mapping.Document

@Document
open class WithName(
    var firstName: String,
    var lastName: String
) : WithId() {

    override fun toString() =
        "WithName(firstName='$firstName', lastName='$lastName')"
}