package com.gabrielecavallo.brooker.events

import com.gabrielecavallo.brooker.domain.entities.Vendor
import org.springframework.context.ApplicationEvent

class VendorDeletedEvent(
    val vendor: Vendor
): ApplicationEvent(vendor)