package com.gabrielecavallo.brooker.events

import com.gabrielecavallo.brooker.domain.entities.User
import org.springframework.context.ApplicationEvent

class UserDeletedEvent(
    val user: User,
    val message: String,
) : ApplicationEvent(user)