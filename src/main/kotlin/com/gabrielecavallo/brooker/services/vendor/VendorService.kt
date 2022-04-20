package com.gabrielecavallo.brooker.services.vendor

import com.gabrielecavallo.brooker.domain.entities.Vendor
import com.gabrielecavallo.brooker.services.common.CRUDService

interface VendorService: CRUDService<Vendor, String> {
    /**
     * Find all the vendors filtered
     */
    fun findAll(filter: VendorFilter): List<Vendor>

    /**
     * Find all the vendors with address
     */
    fun findVendorWithAddress(address: String): List<Vendor>

    /**
     * Find all vendors of country
     */
    fun findVendorOfCountry(country: String): List<Vendor>
}