package com.gabrielecavallo.brooker.web.restful

import com.gabrielecavallo.brooker.domain.entities.Vendor
import com.gabrielecavallo.brooker.services.vendor.VendorFilter
import com.gabrielecavallo.brooker.services.vendor.VendorService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/vendors")
class VendorController(
    val vendorService: VendorService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllVendors(
        @RequestParam firstName: String?,
        @RequestParam lastName: String?,
        @RequestParam country: String?,
        @RequestParam address: String?
    ) = vendorService.findAll(VendorFilter(firstName, lastName, country, address))

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveVendors(@RequestBody vendors: List<Vendor>) =
        vendorService.saveAll(vendors)

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getVendorById(@PathVariable id: String) =
        vendorService.findById(id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun removeVendorById(@PathVariable id: String) =
        vendorService.removeById(id)

    @PutMapping("/{id}")
    fun updateVendorById(@PathVariable id: String, @RequestBody data: Vendor) =
        vendorService.update(id, data)
}