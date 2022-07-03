package org.richard.thymeleaf.tamingthymeleaf

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate
import kotlin.math.ceil

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userGeneratorService: UserGeneratorService

    @BeforeEach
    fun setup() {
        userService.clear()
    }

    @AfterEach
    fun cleanup() {
        userService.clear()
    }

    @Test
    fun testSaveUser() {
        val user = User(
            UserId.newId(),
            UserName("Faus", "Boa"),
            Gender.MALE,
            LocalDate.parse("1989-12-21"),
            EmailAddress("babizon89@hotmail.com"),
            PhoneNumber("651-440-9381")
        )

        userService.save(user)
        assertThat(userService.count()).isEqualTo(1)
    }

    @Test
    fun findSavedUser() {
        val user = User(
            UserId.newId(),
            UserName("Faus", "Boa"),
            Gender.MALE,
            LocalDate.parse("1989-12-21"),
            EmailAddress("babizon89@hotmail.com"),
            PhoneNumber("651-440-9381")
        )

        userService.save(user)
        assertThat(userService.count()).isEqualTo(1)

        val foundUser = userService.findById(user.id)
        assertNotNull(foundUser)
        assertEquals(UserName("Faus", "Boa"), foundUser?.userName)
    }

    @Test
    fun testFindAllPageable() {
        val users = userGeneratorService.generate(20)
        users.forEach { userService.save(it) }

        assertThat(userService.count()).isEqualTo(20)

//        val sort = Sort.by()
        val pagedSource = userService.find(PageRequest.of(1, 5))
        assertThat(pagedSource.getData()).hasSize(5)
        assertThat(pagedSource.getTotalPages()).isEqualTo(4)
    }

    @Test
    fun testCalculateProperPages() {
        val totalElements = 20
        val pageSize = 3
        val pageCounts= ceil(totalElements.toDouble() / pageSize).toInt()
        assertThat(pageCounts).isEqualTo(7)
    }

}