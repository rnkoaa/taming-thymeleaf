package org.richard.thymeleaf.tamingthymeleaf.user

import org.springframework.validation.annotation.Validated
import java.time.LocalDate
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

//@Validated
open class CreateUserFormData(
    @NotNull
    @NotEmpty(message = "first name cannot be null or empty")
    @Size(min = 2, max = 200, message = "first name must be at least 2 characters")
    var firstName: String? = null,

    @NotNull
    @NotEmpty(message = "last name cannot be null or empty")
    @Size(min = 2, max = 200, message = "first name must be at least 2 characters")
    var lastName: String? = null,

//    @NotEmpty
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var birthday: String? = "",

//    @NotBlank
//    @NotEmpty
    var gender: String? = "",

//    @NotBlank
//    @NotEmpty
//    @Email
    var email: String? = "",

//    @NotEmpty
//    @Pattern(regexp = "[0-9.\\-() x/+]+")
    var phoneNumber: String? = ""
) {
    fun toUser(): User {
        val g = when (gender) {
            "Male", "male", "MALE" -> Gender.MALE
            "Female", "female", "FEMALE" -> Gender.FEMALE
            "", "Other", "other", "OTHER" -> Gender.OTHER
            else -> Gender.OTHER
        }

        val b = if (birthday == "") {
            LocalDate.of(1990, 1, 1)
        } else {
            LocalDate.parse(birthday)
        }
        return User(
            UserId.newId(),
            UserName(firstName ?: "", lastName ?: ""), g, b, EmailAddress(email ?: ""),
            PhoneNumber(phoneNumber ?: "")
        )
    }
}