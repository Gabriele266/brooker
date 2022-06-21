package com.gabrielecavallo.brooker.services.user

import com.gabrielecavallo.brooker.common.stringIsObjectId
import com.gabrielecavallo.brooker.common.stringToObjectId
import com.gabrielecavallo.brooker.domain.entities.User
import com.gabrielecavallo.brooker.events.UserDeletedEvent
import com.gabrielecavallo.brooker.exceptions.InvalidIdException
import com.gabrielecavallo.brooker.repositories.UserRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val mongoTemplate: MongoTemplate,
    val applicationEventPublisher: ApplicationEventPublisher
) : UserService {
    override fun save(data: User): User =
        userRepository.save(data)

    override fun saveAll(data: List<User>): List<User> =
        userRepository.saveAll(data)

    override fun findById(id: String): User =
        userRepository.findById(stringToObjectId(id)).orElseThrow {
            throw InvalidIdException(id, "Invalid User reference")
        }

    override fun removeById(id: String): User {
        val userData = findById(id)
        userRepository.deleteById(stringToObjectId(id))

        applicationEventPublisher.publishEvent(UserDeletedEvent(userData, "User removed"))
        return userData
    }

    override fun count(): Long =
        userRepository.count()

    override fun findAll(): List<User> =
        userRepository.findAll()

    override fun findAllOfCountry(country: String): List<User> =
        findAll(UserFilter(country = country))

    override fun findAllWithLastName(lastName: String): List<User> =
        findAll(UserFilter(lastName = lastName))

    override fun findAllWithName(firstName: String): List<User> =
        findAll(UserFilter(firstName))

    override fun findAll(filter: UserFilter): List<User> =
        filter.filter(mongoTemplate)

    override fun update(id: String, data: User): User {
        removeById(id)

        return save(data)
    }

    override fun removeAll() =
        userRepository.deleteAll()

    override fun hasWithId(id: String): Boolean {
        return if (stringIsObjectId(id)) (userRepository.findById(stringToObjectId(id))
            .orElseGet { null } != null) else false
    }
}