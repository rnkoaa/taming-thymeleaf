package org.richard.thymeleaf.tamingthymeleaf.user

import java.time.LocalDate
import javax.validation.constraints.NotBlank

//@Validated
open class CreateUserFormData {

    //    @field:Size(min = 2, max = 200, message = "first name must be at least 2 characters")
//    @field:NotBlank(message = "first name cannot be null or empty")
    @NotBlank(message = "first name cannot be null or empty")
    @field:NotBlank(message = "first name cannot be null or empty")
    var firstName: String? = null

    //    @field:Size(min = 2, max = 200, message = "first name must be at least 2 characters")
//    @field:NotBlank(message = "last name cannot be null or empty")
    @field:NotBlank(message = "last name cannot be null or empty")
    var lastName: String? = null

    //    @NotEmpty
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var birthday: String? = ""

    //    @NotBlank
//    @NotEmpty
    var gender: String? = ""

    //    @NotBlank
//    @NotEmpty
//    @Email
    var email: String? = ""

    //    @NotEmpty
//    @Pattern(regexp = "[0-9.\\-() x/+]+")
    var phoneNumber: String? = ""

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