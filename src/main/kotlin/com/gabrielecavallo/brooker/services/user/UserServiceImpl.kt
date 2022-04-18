package com.gabrielecavallo.brooker.services.user

import com.gabrielecavallo.brooker.common.stringToObjectId
import com.gabrielecavallo.brooker.domain.entities.User
import com.gabrielecavallo.brooker.exceptions.InvalidIdException
import com.gabrielecavallo.brooker.repositories.UserRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val mongoTemplate: MongoTemplate,
) : UserService {
    override fun save(data: User): User =
        userRepository.save(data)

    override fun saveAll(data: List<User>): List<User> =
        userRepository.saveAll(data)

    override fun findById(id: String): User? =
        userRepository.findById(stringToObjectId(id)).orElseThrow {
            throw InvalidIdException(id, "Invalid User reference")
        }

    override fun removeById(id: String): User? {
        userRepository.deleteById(stringToObjectId(id))

        return findById(id)
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

    override fun findAll(filter: UserFilter): List<User> {
        val query = Query()

        if (filter.firstName != null)
            query.addCriteria(Criteria.where("firstName").`is`(filter.firstName))

        if (filter.lastName != null)
            query.addCriteria(Criteria.where("lastName").`is`(filter.lastName))

        if (filter.country != null)
            query.addCriteria(Criteria.where("country").`is`(filter.country))

        return mongoTemplate.find(query, User::class.java)
    }

    override fun update(id: String, data: Any): User {
        TODO("Not yet implemented")
    }

    override fun removeAll() =
        userRepository.deleteAll()
}