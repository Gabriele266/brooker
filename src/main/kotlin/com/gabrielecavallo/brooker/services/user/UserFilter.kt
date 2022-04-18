package com.gabrielecavallo.brooker.services.user

import java.time.LocalDateTime

class UserFilter(
    val firstName: String? = null,
    val lastName: String? = null,
    val country: String? = null,
    val initialBirthDate: LocalDateTime? = null,
    val address: String? = null,
    val endBirthDate: LocalDateTime? = null
) {
}