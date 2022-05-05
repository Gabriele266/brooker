package com.gabrielecavallo.brooker.listeners

import com.gabrielecavallo.brooker.events.VendorDeletedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class VendorDeletedMailListener: ApplicationListener<VendorDeletedEvent> {
    override fun onApplicationEvent(event: VendorDeletedEvent) {
        println("Send mail to vendor when gets deleted")
    }
}