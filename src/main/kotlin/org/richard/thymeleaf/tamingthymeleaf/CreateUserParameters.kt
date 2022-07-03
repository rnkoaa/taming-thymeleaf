package org.richard.thymeleaf.tamingthymeleaf

import java.time.LocalDate

class CreateUserParameters(
    private val userName: UserName,
    val gender: Gender,
    private val birthDay: LocalDate,
    private val email: EmailAddress,
    private val phoneNumber: PhoneNumber
) {

    fun toUser(): User {
        return User(UserId.newId(), userName, gender, birthDay, email, phoneNumber)
    }

    companion object {

        fun creatUser(
            firstName: String,
            lastName: String,
            gender: String,
            birthday: LocalDate,
            email: String,
            phoneNumber: String
        ): CreateUserParameters {
            val g = Gender.valueOf(gender)
            val e = EmailAddress(email)
            val p = PhoneNumber(phoneNumber)
            return CreateUserParameters(UserName(firstName, lastName), g, birthday, e, p)
        }


    }
}
