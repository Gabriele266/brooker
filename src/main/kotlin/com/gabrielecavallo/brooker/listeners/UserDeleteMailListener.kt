package com.gabrielecavallo.brooker.listeners

import com.gabrielecavallo.brooker.events.UserDeletedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class UserDeleteMailListener : ApplicationListener<UserDeletedEvent> {
    override fun onApplicationEvent(event: UserDeletedEvent) {
        println("The user ${event.user.firstName} has been deleted")
    }
}