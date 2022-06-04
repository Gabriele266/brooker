package com.gabrielecavallo.brooker.web.graphql

import com.gabrielecavallo.brooker.exceptions.InvalidIdException
import com.gabrielecavallo.brooker.services.user.UserFilter
import com.gabrielecavallo.brooker.services.user.UserService
import com.gabrielecavallo.brooker.web.graphql.input.UserInput
import com.netflix.graphql.dgs.*
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

@DgsComponent
class UserDataFetcher(
    val userService: UserService
) {
    @DgsQuery(field = "user")
    fun getUser(@InputArgument id: String) = userService.findById(id)

    @DgsQuery(field = "users")
    fun getUsers(
        @InputArgument firstName: String?,
        @InputArgument lastName: String?,
        @InputArgument country: String?,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @InputArgument initialBirthDate: LocalDateTime?,
        @InputArgument address: String?,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @InputArgument endBirthDate: LocalDateTime?
    ) =
        userService.findAll(UserFilter(firstName, lastName, country, initialBirthDate, address, endBirthDate))

    @DgsMutation
    fun addUser(@InputArgument user: UserInput) =
        userService.save(user.toUser())

    @DgsEnableDataFetcherInstrumentation(false)
    @DgsMutation
    @Throws(InvalidIdException::class)
    fun removeUser(@InputArgument id: String) =
        userService.removeById(id)

    @DgsMutation
    fun updateUser(@InputArgument id: String, @InputArgument user: UserInput) =
        userService.update(id, user.toUser())
}