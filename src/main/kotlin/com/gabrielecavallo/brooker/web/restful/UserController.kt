package com.gabrielecavallo.brooker.web.restful

import com.gabrielecavallo.brooker.domain.entities.User
import com.gabrielecavallo.brooker.services.user.UserFilter
import com.gabrielecavallo.brooker.services.user.UserService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.net.Inet4Address
import java.time.LocalDateTime

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllUsers(
        @RequestParam firstName: String?,
        @RequestParam lastName: String?,
        @RequestParam country: String?,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam initialBirthDate: LocalDateTime?,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam endBirthDate: LocalDateTime?,
        @RequestParam address: String?
    ) =
        userService.findAll(UserFilter(firstName, lastName, country, initialBirthDate, address, endBirthDate))

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveUsers(@RequestBody users: List<User>) =
        userService.saveAll(users)

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getUserById(@PathVariable id: String) =
        userService.findById(id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun removeUserById(@PathVariable id: String) =
        userService.removeById(id)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateUserById(@PathVariable id: String, @RequestBody userData: User) =
        userService.update(id, userData)
}