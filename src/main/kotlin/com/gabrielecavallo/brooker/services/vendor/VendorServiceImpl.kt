package com.gabrielecavallo.brooker.services.vendor

import com.gabrielecavallo.brooker.common.stringToObjectId
import com.gabrielecavallo.brooker.domain.entities.Vendor
import com.gabrielecavallo.brooker.events.VendorDeletedEvent
import com.gabrielecavallo.brooker.exceptions.InvalidIdException
import com.gabrielecavallo.brooker.repositories.VendorRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

@Service
class VendorServiceImpl(
    val vendorRepository: VendorRepository,
    val mongoTemplate: MongoTemplate,
    val applicationEventPublisher: ApplicationEventPublisher
) : VendorService {
    override fun save(data: Vendor): Vendor =
        vendorRepository.save(data)

    override fun saveAll(data: List<Vendor>): List<Vendor> =
        vendorRepository.saveAll(data)

    override fun findById(id: String): Vendor =
        vendorRepository.findById(stringToObjectId(id)).orElseThrow { InvalidIdException(id) }

    override fun findAll(): List<Vendor> =
        vendorRepository.findAll()

    override fun count(): Long =
        vendorRepository.count()

    override fun removeById(id: String): Vendor {
        val initial = findById(id)

        vendorRepository.deleteById(stringToObjectId(id))
        applicationEventPublisher.publishEvent(VendorDeletedEvent(initial))
        return initial
    }

    override fun removeAll() =
        vendorRepository.deleteAll()

    override fun update(id: String, data: Vendor): Vendor {
        removeById(id)
        return save(data)
    }

    override fun findAll(filter: VendorFilter): List<Vendor> =
        filter.filter(mongoTemplate)

    override fun findVendorWithAddress(address: String): List<Vendor> =
        findAll(VendorFilter(address = address))

    override fun findVendorOfCountry(country: String): List<Vendor> =
        findAll(VendorFilter(country = country))
}