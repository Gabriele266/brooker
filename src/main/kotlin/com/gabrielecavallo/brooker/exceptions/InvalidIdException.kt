package com.gabrielecavallo.brooker.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class InvalidIdException(val id: String): Exception("Invalid id string for ObjectId: $id")