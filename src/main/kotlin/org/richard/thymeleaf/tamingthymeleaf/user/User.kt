package org.richard.thymeleaf.tamingthymeleaf.user

import java.time.LocalDate
import java.util.*

data class PhoneNumber(val value: String) {

}

data class User(
    val id: UserId,
    val userName: UserName,
    val gender: Gender,
    val birthDate: LocalDate,
    val email: EmailAddress,
    val phoneNumber: PhoneNumber,
) {
}

data class UserId(val value: UUID) {


    companion object {
        fun newId(): UserId {
            return UserId(UUID.randomUUID())
        }

        fun of(id: String): UserId {
            return UserId(UUID.fromString(id))
        }
    }

    override fun toString(): String {
        return value.toString()
    }

}

data class EmailAddress(val value: String) {

    override fun toString(): String {
        return value.lowercase()
    }
}

enum class Gender {
    MALE,
    FEMALE,
    OTHER;

    override fun toString(): String {
        return when (this) {
            MALE -> "Male"
            FEMALE -> "Female"
            OTHER -> "Other"
        }
    }
}

data class UserName(val firstName: String, val lastName: String) {

    fun getFullName(): String {
        return "$firstName $lastName"
    }
}
