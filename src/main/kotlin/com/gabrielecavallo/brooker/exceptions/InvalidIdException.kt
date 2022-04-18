package com.gabrielecavallo.brooker.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class InvalidIdException(val id: String, message: String? = null) : Exception("$id is not valid. $message") {
    override fun toString(): String =
        "InvalidIdException: $message"
}