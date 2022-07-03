package org.richard.thymeleaf.tamingthymeleaf;

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component


@Component
@Profile("!test")
class ApplicationStarted(
    private val userGeneratorService: UserGeneratorService,
    private val userService: UserService
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        for (index in 0 until 20) {
            userService.save(userGeneratorService.createUser())
        }
    }

}
