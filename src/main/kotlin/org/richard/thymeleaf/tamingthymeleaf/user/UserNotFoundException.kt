package org.richard.thymeleaf.tamingthymeleaf.user

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException(private val userId: UserId) : RuntimeException(
    "User with id ${userId.value} not found"
) {
}