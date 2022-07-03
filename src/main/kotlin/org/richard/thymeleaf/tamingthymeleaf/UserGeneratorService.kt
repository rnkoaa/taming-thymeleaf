package org.richard.thymeleaf.tamingthymeleaf;

import com.github.javafaker.Faker
import org.springframework.stereotype.Component;
import java.time.LocalDate
import java.time.ZoneId

@Component
class UserGeneratorService {

    fun generate(count: Int): List<User> {
        val items = mutableListOf<User>()
        repeat((0 until count).count()) {
            items.add(createUser())
        }

        return items.toList()
    }


    fun createUser(): User {
        val faker = Faker()
        val name = faker.name();
        val userName = UserName(name.firstName(), name.lastName())
        val gender = if (faker.bool().bool()) Gender.MALE else Gender.FEMALE
        val birthday = LocalDate.ofInstant(
            faker.date().birthday(
                10, 40
            ).toInstant(), ZoneId.systemDefault()
        )

        val email = EmailAddress(faker.internet().emailAddress(generateEmailLocalPart(userName)))
        val phoneNumber = PhoneNumber(faker.phoneNumber().phoneNumber())

        return User(
            UserId.newId(), userName, gender, birthday, email, phoneNumber
        )
    }

    private fun generateEmailLocalPart(userName: UserName): String {
        val firstName = userName.firstName.replace("'", "")
        val lastName = userName.lastName.replace("'", "")
        return "$firstName.$lastName"
    }
}
